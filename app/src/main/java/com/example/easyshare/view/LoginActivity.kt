package com.example.easyshare.view

import LoginViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.easyshare.R
import com.example.easyshare.di.injectModuleDependencies
import com.example.easyshare.di.parseAndInjectConfiguration
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var loginButton: Button
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        parseAndInjectConfiguration()
        injectModuleDependencies(this)

        loginButton = findViewById(R.id.button)
        emailInput = findViewById(R.id.emailEt)
        passwordInput = findViewById(R.id.passET)

        loginViewModel.loginResult.observe(this, Observer { loginResponse ->

        })

        loginViewModel.loginError.observe(this, Observer { errorMessage ->

        })

        loginButton.setOnClickListener {
            this.login()
        }
    }

    fun login() {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            println("login")
            loginViewModel.login(email, password)
        } else {
            Toast.makeText(this, "merci de remplire tous les champs", Toast.LENGTH_SHORT).show()
        }
    }
}