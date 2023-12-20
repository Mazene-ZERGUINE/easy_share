package com.example.easyshare.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyshare.databinding.ActivityProductDetailsBinding
import com.example.easyshare.view.adapters.CommentsAdapter
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_ID
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding

    private lateinit var productId: String
    private lateinit var productTitle: String

    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductInfoFromIntent()
        setProductInfoInView()
        setUpComments()
    }

    private fun setUpComments() {
    }

    private fun setProductInfoInView() {
        binding.productDetailTitleTv.text = this.productTitle
    }

    private fun getProductInfoFromIntent() {
        productId = intent.getStringExtra(PRODUCT_ID) ?: ""
        productTitle = intent.getStringExtra(PRODUCT_NAME) ?: ""
    }
}
