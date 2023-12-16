package com.example.easy_share.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easy_share.dummy.FakeProductService
import com.example.easy_share.models.ProductData
import com.example.easy_share.repositories.FakeProductRepository
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class ProductViewModel (private val productRepository: FakeProductRepository ): ViewModel(){
    private val disposBag = CompositeDisposable()

    private val productData: BehaviorSubject<List<ProductData>> = BehaviorSubject.createDefault(listOf())

    val completeProductData: MutableLiveData<List<ProductData>> = MutableLiveData()

    init {
        getCompleteProductData()
    }

    private fun getProduct(){

        productRepository.getFakeProducts()
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    this.productData.onNext(it)
                },
                {
                    Log.d("getFakeProducts", "Error while getting products data ")
                }
            ).addTo(disposBag)

    }

    private fun getCompleteProductData(){
        this.getProduct()

        this.productData
            .delay(2, TimeUnit.SECONDS)
            .observeOn(Schedulers.io())
            .subscribe{
                print(it)
                this.completeProductData.postValue(it)
            }.addTo(disposBag)
    }

}