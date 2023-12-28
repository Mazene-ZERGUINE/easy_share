package com.example.easyshare.ui.fragments

import Utils
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.easyshare.R
import com.example.easyshare.ui.view.LoginActivity
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
    private lateinit var deleteButton: ImageView
    private var userId: Int? = null

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
        deleteButton = rootView.findViewById(R.id.deleteAccountIcon)

        setUserData()

        deleteButton.setOnClickListener {
            deleteAccount()
        }

        return rootView
    }

    private fun setUserData() {
        accountViewModel.getUserByPseudonyme(pseudonyme)

        accountViewModel.getUserDataResult.observe(viewLifecycleOwner) { response ->
            userId = response.utilisateurId

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

    private fun deleteAccount() {
        setOverlay()
        val message = "Êtes-vous sûr(e) de vouloir supprimer votre compte ?"
        val dialog = Dialog(requireContext())
        Utils.showCustomDialogBox(message, dialog) { result ->
            if (result) {
                userId?.let { accountViewModel.deleteUserAccount(it) }

                accountViewModel.deleteAccountResult.observe(viewLifecycleOwner) { response ->
                    println(response)
                    val intent = Intent(this.requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                }

                accountViewModel.deleteAccountError.observe(viewLifecycleOwner) { err ->
                    err.printStackTrace()
                    Utils.displayToast(this.requireContext(), R.layout.error_toast, "Une erreur est survenu !", Toast.LENGTH_SHORT)
                }
            } else {
                clearOverlay()
            }
        }
    }

    private fun setOverlay() {
        val overlayLayout = this.requireActivity().findViewById<View>(R.id.overlayLayout)
        overlayLayout.visibility = View.VISIBLE
    }

    private fun clearOverlay() {
        val overlayLayout = this.requireActivity().findViewById<View>(R.id.overlayLayout)
        overlayLayout.visibility = View.GONE
    }
}
