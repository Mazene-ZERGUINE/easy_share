package com.example.easyshare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.easyshare.R
import com.example.easyshare.ui.viewmodel.AccountViewModel
import com.example.easyshare.utilis.TokenManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {
    private val accountViewModel: AccountViewModel by viewModel()

    private val pseudonyme: String = TokenManager.getPseudonymeFromToken()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUserData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    private fun setUserData() {
        println(pseudonyme)
        accountViewModel.getUserByPseudonyme(pseudonyme)

        accountViewModel.getUserDataResult.observe(this@AccountFragment) { response ->
            println(response)
        }

        accountViewModel.getUserError.observe(this@AccountFragment) { err ->
            println(err)
            err.printStackTrace()
        }
    }
}
