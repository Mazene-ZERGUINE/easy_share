import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyshare.models.LoginRequest
import com.example.easyshare.models.LoginResponse
import com.example.easyshare.repositories.AuthRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val disposBag = CompositeDisposable()

    private val _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse> = _loginResult

    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> = _loginError

    fun login(
        email: String,
        password: String
    ) {
        val payload = LoginRequest(email, password)

        authRepository.login(payload)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response -> _loginResult.postValue(response) },
                { error -> _loginError.postValue(error.message) }
            ).addTo(disposBag)
    }
}
