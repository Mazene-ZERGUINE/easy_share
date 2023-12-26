package com.example.easyshare.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.models.Data
import com.example.easyshare.repositories.ProductsRepository
import com.example.easyshare.repositories.UserRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ProductViewModel(
    private val productRepository: ProductsRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val disposBag = CompositeDisposable()
    private val starPosts = mutableMapOf<String, Boolean>() // <postId, starred>
    private val productData: BehaviorSubject<List<Data>> = BehaviorSubject.createDefault(listOf())

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    val completeProductData: MutableLiveData<List<Data>> = MutableLiveData()

    init {
        getCompleteProductData()
    }

    fun isPostStarred(key: String): Boolean? {
        return starPosts[key]
    }

    fun reloadPosts() {
        this.getProducts()
    }

    fun starPost(id: Int) {
        this.userRepository.starPost(id)
            .observeOn(Schedulers.io())
            .subscribe(
                {
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
            .observeOn(Schedulers.io())
            .doOnTerminate { this.isLoading.postValue(false) }
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
}
