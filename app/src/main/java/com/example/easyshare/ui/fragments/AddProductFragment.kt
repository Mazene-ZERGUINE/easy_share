package com.example.easyshare.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.easyshare.R
import com.example.easyshare.databinding.FragmentAddProductBinding
import com.example.easyshare.ui.view.MainActivity
import com.example.easyshare.ui.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductFragment : Fragment() {
    private val productViewModel: ProductViewModel by viewModel()

    private lateinit var binding: FragmentAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)

        this.binding.addProductBtn.setOnClickListener {
            this.addNewProduct()
        }

        return binding.root
    }

    private fun addNewProduct() {
        val productTitle = this.binding.titleProductInput.text.toString()
        val productDescription = this.binding.descriptionProductInput.text.toString()

        if (productTitle.isNotEmpty() && productDescription.isNotEmpty()) {
            productViewModel.addNewProduct(productTitle, productDescription)
            val intent = Intent(this.requireActivity(), MainActivity::class.java)
            startActivity(intent)
        } else {
            Utils.displayToast(requireContext(), R.layout.error_toast, "Merci de remplire tous les champs!", Toast.LENGTH_SHORT)
        }
    }
}
