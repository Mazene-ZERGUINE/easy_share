package com.example.easyshare.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.models.SignupRequest
import com.example.easyshare.models.SignupResponse
import com.example.easyshare.repositories.AuthRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class SignupViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val disposeBag = CompositeDisposable()
    val signupResult = MutableLiveData<SignupResponse>()
    val signupErr = MutableLiveData<Throwable>()

    fun signup(
        email: String,
        password: String,
        userName: String
    ) {
        val payload = SignupRequest(email, password, userName)
        this.authRepository.signup(payload)
            .observeOn(Schedulers.io())
            .subscribeBy(
                onNext = { signupResponse ->
                    println(signupResponse)
                    signupResult.postValue(signupResponse)
                },
                onError = { error ->
                    signupErr.postValue(error)
                }
            )
            .addTo(disposeBag)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}
