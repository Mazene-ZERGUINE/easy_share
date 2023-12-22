package com.example.easyshare.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.models.Commentaire
import com.example.easyshare.repositories.ProductsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CommentViewModel(private val productsRepository: ProductsRepository) : ViewModel() {
    private val disposBag = CompositeDisposable()

    private val productComments: BehaviorSubject<List<Commentaire>> = BehaviorSubject.createDefault(listOf())

    val isLimitEnabled: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val completeComments: MutableLiveData<List<Commentaire>> = MutableLiveData()

    fun loadComments(productId: Int) {
        getAllComments(productId)
    }

    private fun getComments(publicationId: Int) {
        productsRepository.getAllProductComments(publicationId)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    this.productComments.onNext(it.data.rows)
                    Log.d("gettingData", it.data.rows.toString())
                },
                { e ->
                    Log.e("getComments", "failed to get comments", e)
                }
            ).addTo(disposBag)
    }

    private fun getAllComments(publicationId: Int) {
        this.getComments(publicationId)
        Observable.combineLatest(
            this.productComments.observeOn(Schedulers.io()),
            this.isLimitEnabled.observeOn(Schedulers.io()),
            BiFunction {
                    comments: List<Commentaire>, isLimited: Boolean ->
                if (!isLimited) comments.take(5) else comments
            }
        ).subscribe({
            this.completeComments.postValue(it)
        }, {
            Log.d("getAllComments", "failed to get comments")
        }).addTo(disposBag)
    }
}
