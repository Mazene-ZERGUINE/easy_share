package com.example.easyshare.view

import Utils
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easyshare.R
import com.example.easyshare.viewmodel.SignupViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

class SignupActivity : AppCompatActivity() {
    private val signupViewModel: SignupViewModel by viewModel()

    private lateinit var passwordInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var usernameInput: TextInputEditText
    private lateinit var signupButton: Button
    private lateinit var loginTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        passwordInput = findViewById(R.id.passET)
        usernameInput = findViewById(R.id.passCET)
        emailInput = findViewById(R.id.emailEt)
        signupButton = findViewById(R.id.button)
        loginTv = findViewById(R.id.loginTv)

        observeSignupSuccess()
        observeSignupFailure()

        loginTv.setOnClickListener {
            redirectToLoginActivity()
        }

        signupButton.setOnClickListener {
            register()
        }
    }

    private fun observeSignupSuccess() {
        signupViewModel.signupResult.observe(this@SignupActivity) {
            Utils.displayToast(
                applicationContext,
                R.layout.success_toast,
                "Un email de confirmation à été envoyé",
                Toast.LENGTH_SHORT
            )
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    private fun observeSignupFailure() {
        signupViewModel.signupErr.observe(this@SignupActivity) {
            if (it is HttpException) {
                val errCode = it.code()
                if (errCode == 409) {
                    Utils.displayToast(
                        applicationContext,
                        R.layout.error_toast,
                        "Email ou nom d'utilisateur exist déja",
                        Toast.LENGTH_SHORT
                    )
                }
            } else {
                Utils.displayToast(
                    applicationContext,
                    R.layout.error_toast,
                    "Une erreur se produit essayez plus tard",
                    Toast.LENGTH_SHORT
                )
            }
        }
    }

    private fun register() {
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()
        val username = usernameInput.text.toString().trim()

        if (email.isEmpty() && password.isNotEmpty() && username.isEmpty()) {
            Utils.displayToast(
                applicationContext,
                R.layout.error_toast,
                "Merci de remplire tous les champs!",
                Toast.LENGTH_SHORT
            )
            return
        }

        if (password.length < 6) {
            Utils.displayToast(
                applicationContext,
                R.layout.error_toast,
                "Le mot de passe doit contenir au moins 6 caractères.",
                Toast.LENGTH_SHORT
            )
            return
        }

        signupViewModel.signup(email, password, username)
    }

    private fun redirectToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
