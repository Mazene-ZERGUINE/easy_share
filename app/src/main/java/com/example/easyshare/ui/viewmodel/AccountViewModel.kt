package com.example.easyshare.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.models.UserData
import com.example.easyshare.repositories.UserRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class AccountViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val disposeBag = CompositeDisposable()

    val getUserDataResult = MutableLiveData<UserData>()
    val getUserError = MutableLiveData<Throwable>()

    fun getUserByPseudonyme(pseudonyme: String) {
        userRepository.getUserData(pseudonyme).observeOn(Schedulers.io()).subscribeBy(
            onNext = { getUserResponse ->
                getUserDataResult.postValue(getUserResponse)
            },
            onError = { getUserResponse ->
                getUserError.postValue(getUserResponse)
            }
        ).addTo(disposeBag)
    }
}
