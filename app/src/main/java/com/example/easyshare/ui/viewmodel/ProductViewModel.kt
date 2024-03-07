package com.example.easyshare.ui.viewmodel

import Utils
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.R
import com.example.easyshare.models.Data
import com.example.easyshare.models.ImageData
import com.example.easyshare.models.NewProductRequest
import com.example.easyshare.repositories.ProductsRepository
import com.example.easyshare.repositories.UserRepository
import com.example.easyshare.ui.view.MainActivity
import com.example.easyshare.utilis.TokenManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.time.Duration

class ProductViewModel(
    private val productRepository: ProductsRepository,
    private val userRepository: UserRepository,
    private val context: Context
) : ViewModel() {
    private val disposBag = CompositeDisposable()
    private val starPosts = mutableMapOf<String, Boolean>()
    private val productData: BehaviorSubject<List<Data>> = BehaviorSubject.createDefault(listOf())


    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    val completeProductData: MutableLiveData<List<Data>> = MutableLiveData()

    val userProductData: MutableLiveData<List<Data>> = MutableLiveData()

    val isPostStarredData = MutableLiveData<Boolean>()

    val imageData: MutableLiveData<List<ImageData>> = MutableLiveData()


    init {
        getCompleteProductData()
    }

    fun isPostStarred(key: String): Boolean? {
        return starPosts[key]
    }

    fun reloadPosts() {
        this.getProducts()
        this.getProductByUserId()
    }

    fun starPost(id: Int) {
        this.userRepository.starPost(id)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    this.isPostStarredData.postValue(true)
                },
                { e ->
                    Log.d("starPost", "Error while star post")
                }
            ).addTo(disposBag)
    }

    fun unstarPost(id: Int) {
        this.userRepository.unstarPost(id)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    this.isPostStarredData.postValue(false)
                },
                { e ->
                    Log.d("starPost", "Error while star post")
                }
            ).addTo(disposBag)
    }

    private fun getIsPostStarred(id: Int): Observable<Boolean> {
        return this.userRepository.isStarredPost(id)
            .observeOn(Schedulers.io())
            .map {
                this.starPosts[id.toString()] = it.data.starred
                return@map it.data.starred
            }
            .onErrorReturn { _ ->
                Log.d("getIsStarredPost", "Error while getting getIsStarredPost")
                false
            }
    }

    private fun getProducts() {
        productRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { this.isLoading.postValue(true) }
            .flatMapObservable { response ->
                this.productData.onNext(response.data)
                Observable.fromIterable(response.data)
                    .flatMap { data ->
                        getIsPostStarred(data.publicationId).map { isStarred ->
                            Pair(
                                data,
                                isStarred
                            )
                        }
                    }
                    .toList()
                    .toObservable()
            }
            .doFinally {
                this.isLoading.postValue(false)
            }
            .subscribe(
                { pairs ->
                    pairs.map { (data, isStarred) ->
                        starPosts.put(data.publicationId.toString(), isStarred)
                    }
                },
                { e ->
                    Log.e("getProducts", "Error while getting products data", e)
                }
            ).addTo(disposBag)
    }

    private fun getCompleteProductData() {
        this.getProducts()

        this.productData
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("getProduct", it.toString())
                this.completeProductData.postValue(it)
            }.addTo(disposBag)
    }

    fun addNewProduct(
        title: String,
        description: String,
        imageUri: Uri
    ) {
        val imageFilePath = getImageFilePath(imageUri)
        if (imageFilePath == null) {
            Log.e("addNewProduct", "Image file path is null")
            return
        }

        val imageFile = File(imageFilePath)
        val imageRequestBody = imageFile.asRequestBody(imageFile.extension.toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, imageRequestBody)

        val titleRequestBody = title.toRequestBody(MultipartBody.FORM)
        val descriptionRequestBody = description.toRequestBody(MultipartBody.FORM)
        val userIdRequestBody = TokenManager.getUserIdFromToken().toString().toRequestBody(MultipartBody.FORM)

        productRepository.addNewProduct(titleRequestBody, descriptionRequestBody, imagePart , userIdRequestBody)
            .subscribe()
            .addTo(disposBag)
    }


    fun getProductImage(imageId: Int) {
        productRepository.getProductImage(imageId)
            .observeOn(Schedulers.io())
            .subscribe(
                { imageData ->
                    Log.d("data ------ ", imageData.toString())
                    this.imageData.postValue(imageData)
                },
                { error ->
                    Log.d("fuck this shit ----------------------- "," ------------------------------ Error fetching product image: $error")
                }
            )
            .addTo(disposBag)
    }

    private fun getImageFilePath(uri: Uri): String? {
        var imagePath: String? = null
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                imagePath = it.getString(columnIndex)
            }
        }
        return imagePath
    }

    private fun getProductByUserId() {
        val currentUserId = TokenManager.getUserIdFromToken()

        productRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { response ->

                response.data.filter { it.utilisateurId == currentUserId }
            }
            .subscribe(
                { filteredProducts ->
                    userProductData.postValue(filteredProducts)
                    Log.d("getProductByUserId", filteredProducts.toString())
                },
                { error ->
                    Log.e("getProductByUserId", "Error fetching user products", error)
                }
            ).addTo(disposBag)
    }
}
