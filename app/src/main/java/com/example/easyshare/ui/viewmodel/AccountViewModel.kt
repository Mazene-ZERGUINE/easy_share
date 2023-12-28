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

    val deleteAccountResult = MutableLiveData<Unit>()
    val deleteAccountError = MutableLiveData<Throwable>()

    val updateUserResult = MutableLiveData<Unit>()
    val updateUserError = MutableLiveData<Throwable>()

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

    fun deleteUserAccount(userId: Int) {
        userRepository.deleteUser(userId).observeOn(Schedulers.io()).subscribeBy(
            onNext = { deleteUserResponse ->
                deleteAccountResult.postValue(deleteUserResponse)
            },
            onError = { deleteUserErr ->
                deleteAccountError.postValue(deleteUserErr)
            }
        ).addTo(disposeBag)
    }

    fun updateUser(
        payload: UserData,
        userId: Int
    ) {
        userRepository.updateUserProfile(payload, userId).observeOn(Schedulers.io()).subscribeBy(
            onNext = { updateUserResponse ->
                updateUserResult.postValue(updateUserResponse)
            },
            onError = { updateUserErr ->
                updateUserError.postValue(updateUserErr)
            }
        ).addTo(disposeBag)
    }
}
