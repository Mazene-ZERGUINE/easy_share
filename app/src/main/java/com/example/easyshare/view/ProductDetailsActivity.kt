package com.example.easyshare.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.easyshare.databinding.ActivityProductDetailsBinding
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME
import com.example.easyshare.viewmodel.CommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsActivity : AppCompatActivity() {
    private val commentViewModel: CommentViewModel by viewModel()

    private lateinit var binding: ActivityProductDetailsBinding

    private lateinit var productTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductInfoFromIntent()
        setProductInfoInView()
        observeProductComments()
    }

    private fun getProductInfoFromIntent() {
        productTitle = intent.getStringExtra(PRODUCT_NAME) ?: ""
    }

    private fun setProductInfoInView() {
        binding.productDetailTitleTv.text = this.productTitle
    }

    private fun observeProductComments() {
        this.commentViewModel.completeComments.observe(this) { comments ->
            Log.d("commentsDetails", comments.toString())
        }
    }
}
