package com.example.easyshare.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.models.Commentaire
import com.example.easyshare.repositories.ProductsRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CommentViewModel(private val productsRepository: ProductsRepository) : ViewModel() {
    private val disposBag = CompositeDisposable()

    private val productComments: BehaviorSubject<List<Commentaire>> = BehaviorSubject.createDefault(listOf())

    val completeComments: MutableLiveData<List<Commentaire>> = MutableLiveData()

    init {
        this.getAllComments()
    }

    private fun getComments()  {
        productsRepository.getAllProductComments()
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    this.productComments.onNext(it.rows)
                    Log.d("gettingData", it.rows.toString())
                },
                { e ->
                    Log.e("getComments", "failed to get comments", e)
                }
            ).addTo(disposBag)
    }

    private fun getAllComments()  {
        this.getComments()
        this.productComments.observeOn(Schedulers.io())
            .subscribe({
                this.completeComments.postValue(it)
            }, {
                Log.d("getAllComments", "failed to get comments")
            }).addTo(disposBag)
    }
}
