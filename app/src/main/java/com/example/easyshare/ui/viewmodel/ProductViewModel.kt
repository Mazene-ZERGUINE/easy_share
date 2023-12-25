package com.example.easyshare.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.models.Data
import com.example.easyshare.repositories.ProductsRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ProductViewModel(private val productRepository: ProductsRepository) : ViewModel() {
    private val disposBag = CompositeDisposable()

    private val productData: BehaviorSubject<List<Data>> = BehaviorSubject.createDefault(listOf())

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    val completeProductData: MutableLiveData<List<Data>> = MutableLiveData()

    init {
        getCompleteProductData()
    }

    private fun getProduct() {
        productRepository.getProducts()
            .observeOn(Schedulers.io())
            .doOnTerminate { this.isLoading.postValue(false) }
            .subscribe(
                {
                    this.productData.onNext(it.data)
                    Log.d("commentsSize", it.data[0].comments.count().toString())
                },
                { e ->
                    Log.e("getProducts", "Error while getting products data", e)
                }
            ).addTo(disposBag)
    }

    private fun getCompleteProductData() {
        this.getProduct()

        this.productData
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("getProduct", it.toString())
                this.completeProductData.postValue(it)
            }.addTo(disposBag)
    }
}
