package com.example.easyshare.ui.view

import LoginViewModel
import Utils
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easyshare.R
import com.example.easyshare.di.injectModuleDependencies
import com.example.easyshare.di.parseAndInjectConfiguration
import com.example.easyshare.utilis.TokenManager
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var loginButton: Button
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var signupTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        parseAndInjectConfiguration()
        injectModuleDependencies(this)

        loginButton = findViewById(R.id.button)
        emailInput = findViewById(R.id.emailEt)
        passwordInput = findViewById(R.id.passET)
        signupTv = findViewById(R.id.textView)

        observeLoginError()
        observeLoginSuccess()

        loginButton.setOnClickListener {
            this.login()
        }

        signupTv.setOnClickListener {
            redirectToSignupScreen()
        }
    }

    private fun observeLoginError() {
        loginViewModel.loginError.observe(this) { _ ->
            Utils.displayToast(
                applicationContext,
                R.layout.error_toast,
                "Ce compte n'éxiste pas",
                Toast.LENGTH_SHORT
            )
        }
    }

    private fun login() {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            loginViewModel.login(email, password)
        } else {
            Utils.displayToast(applicationContext, R.layout.error_toast, "Merci de remplire tous les champs!", Toast.LENGTH_SHORT)
        }
    }

    private fun observeLoginSuccess() {
        loginViewModel.loginResult.observe(this) { loginResponse ->
            val token: String = loginResponse.getToken()
            TokenManager.setToken(token)
            TokenManager.setPseudonymeFromToken()

            Utils.displayToast(
                applicationContext,
                R.layout.success_toast,
                "Bonjour ^^",
                Toast.LENGTH_SHORT
            )
            TokenManager.storeAccessToken(this, loginResponse.getToken())
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
        }
    }

    private fun redirectToSignupScreen() {
        val signupIntent = Intent(this, SignupActivity::class.java)
        startActivity(signupIntent)
    }
}
