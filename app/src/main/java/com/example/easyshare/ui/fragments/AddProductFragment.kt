import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easyshare.databinding.FragmentAddProductBinding
import com.example.easyshare.ui.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductFragment : Fragment() {
    private val productViewModel: ProductViewModel by viewModel()

    private lateinit var binding: FragmentAddProductBinding
    private lateinit var addImageBtn: Button

    private var selectedImageUri: Uri? = null

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedImageUri = it
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)

        binding.addImgProductBtn.setOnClickListener {
            Log.d("fuck: ", "what the fuck is your probleme")
            pickImage.launch("image/*")
        }

        binding.addProductBtn.setOnClickListener {
            // addNewProduct()
        }

        return binding.root
    }

    private fun addNewProduct() {
        Log.d("err", "weird error")
        val productTitle = binding.titleProductInput.text.toString()
        val productDescription = binding.descriptionProductInput.text.toString()

        if (productTitle.isNotEmpty() && productDescription.isNotEmpty() && selectedImageUri != null) {
            productViewModel.addNewProduct(productTitle, productDescription, selectedImageUri)

            findNavController().popBackStack()
        } else {
            Toast.makeText(
                requireContext(),
                "Merci de remplire tous les champs et ajouter une image!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
