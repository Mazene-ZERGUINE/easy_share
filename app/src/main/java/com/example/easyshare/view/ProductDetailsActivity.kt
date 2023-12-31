package com.example.easyshare.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyshare.databinding.ActivityProductDetailsBinding
import com.example.easyshare.view.adapters.CommentsAdapter
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_CREATED_AT
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_ID
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME
import com.example.easyshare.viewmodel.CommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsActivity : AppCompatActivity() {
    private val commentViewModel: CommentViewModel by viewModel()

    private lateinit var binding: ActivityProductDetailsBinding

    private lateinit var productTitle: String
    private lateinit var productId: String
    private lateinit var productCreatedAt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductInfoFromIntent()
        setProductInfoInView()
        observeProductComments()

        getMoreComments()
    }



    private fun getProductInfoFromIntent() {
        productTitle = intent.getStringExtra(PRODUCT_NAME) ?: ""
        productId = intent.getStringExtra(PRODUCT_ID) ?: ""
        productCreatedAt = intent.getStringExtra(PRODUCT_CREATED_AT) ?: ""

        productId?.let {
            this.commentViewModel.loadComments(it.toInt())
        }
    }

    private fun setProductInfoInView() {
        binding.productDetailTitleTv.text = this.productTitle
        binding.productDetailCreatedAtTv.text = this.productCreatedAt
    }

    private fun observeProductComments() {
        this.commentViewModel.completeComments.observe(this) { comments ->
            binding.commentsRv.layoutManager = LinearLayoutManager(this)
            binding.commentsRv.adapter = CommentsAdapter(comments)
        }
    }
    private fun getMoreComments() {
        binding.toggleLimitButton.setOnClickListener {
            commentViewModel.setIsLimitedComments()
        }
    }
}
