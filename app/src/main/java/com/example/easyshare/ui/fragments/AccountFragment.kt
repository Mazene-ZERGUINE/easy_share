package com.example.easyshare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.easyshare.R
import com.example.easyshare.ui.viewmodel.AccountViewModel
import com.example.easyshare.utilis.TokenManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {
    private val accountViewModel: AccountViewModel by viewModel()

    private val pseudonyme: String = TokenManager.getPseudonymeFromToken()
    private lateinit var userNameTv: TextView
    private lateinit var emailTv: TextView
    private lateinit var nameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var userNameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var editButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_account, container, false)
        userNameTv = rootView.findViewById(R.id.userNameTextView)
        emailTv = rootView.findViewById(R.id.userEmailTextView)
        nameInput = rootView.findViewById(R.id.editTextName)
        lastNameInput = rootView.findViewById(R.id.editTextLastName)
        emailInput = rootView.findViewById(R.id.editTextEmail)
        userNameInput = rootView.findViewById(R.id.editTextUserName)
        passwordInput = rootView.findViewById(R.id.editTextPassword)
        editButton = rootView.findViewById(R.id.editButton)

        setUserData()

        return rootView
    }

    private fun setUserData() {
        println(pseudonyme)
        accountViewModel.getUserByPseudonyme(pseudonyme)

        accountViewModel.getUserDataResult.observe(viewLifecycleOwner) { response ->
            userNameTv.text = response.pseudonyme
            emailTv.text = response.email

            userNameInput.setText(response.pseudonyme)
            nameInput.setText(response.nom)
            lastNameInput.setText(response.prenom)
            emailInput.setText(response.email)
        }

        accountViewModel.getUserError.observe(viewLifecycleOwner) { err ->
        }
    }
}
