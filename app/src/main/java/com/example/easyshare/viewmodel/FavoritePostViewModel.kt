package com.example.easyshare.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.models.ApiResponse
import com.example.easyshare.models.DataProperty
import com.example.easyshare.models.PublicationFavori
import com.example.easyshare.repositories.UserRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class FavoritePostViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val disposeBag = CompositeDisposable()
    private val _favoritePosts =
        BehaviorSubject.createDefault(
            ApiResponse<PublicationFavori>(DataProperty(0, listOf()))
        )

    val isLoading = MutableLiveData(true)
    val favoritePosts = MutableLiveData<ApiResponse<PublicationFavori>>()

    init {
        this.getFavoritePosts()
        this.observeOnFavoritePosts()
    }

    private fun getFavoritePosts() {
        this.userRepository
            .getFavoritePosts()
            .observeOn(Schedulers.io())
            .doOnTerminate { this.isLoading.postValue(false) }
            .subscribe(
                {
                    this._favoritePosts.onNext(it)
                },
                { e ->
                    Log.d("getFavoritePosts", "Error while getting users data from API")
                }
            )
            .addTo(disposeBag)
    }

    private fun observeOnFavoritePosts() {
        this._favoritePosts
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("👉 observeOnFavoritePosts", it.toString())
                this.favoritePosts.postValue(it)
            }
            .addTo(disposeBag)
    }
}
