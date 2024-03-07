package com.example.easyshare.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easyshare.R
import com.example.easyshare.databinding.FragmentAddProductBinding
import com.example.easyshare.ui.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddProductFragment : Fragment() {
    private val productViewModel: ProductViewModel by viewModel { parametersOf(requireContext()) }

    private lateinit var binding: FragmentAddProductBinding
    private lateinit var imageUri: Uri

    private val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it!!
        binding.selectedImage.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)

        binding.addImgProductBtn.setOnClickListener {
            imagePicker.launch("image/*")
        }


        this.binding.addProductBtn.setOnClickListener {
            this.addNewProduct()
        }

        return binding.root
    }

    private fun addNewProduct() {
        val productTitle = this.binding.titleProductInput.text.toString()
        val productDescription = this.binding.descriptionProductInput.text.toString()

        if (productTitle.isNotEmpty() && productDescription.isNotEmpty()) {
            productViewModel.addNewProduct(productTitle, productDescription, imageUri)

            findNavController().popBackStack()
        } else {
            Utils.displayToast(requireContext(), R.layout.error_toast, "Merci de remplire tous les champs!", Toast.LENGTH_SHORT)
        }
    }

}
