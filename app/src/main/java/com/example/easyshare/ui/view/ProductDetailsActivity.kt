package com.example.easyshare.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyshare.R
import com.example.easyshare.databinding.ActivityProductDetailsBinding
import com.example.easyshare.ui.view.adapters.CommentsAdapter
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_COMMENTS_SIZE
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_CREATED_AT
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_CREATED_BY
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_DESCRIPTION
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_ID
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME
import com.example.easyshare.ui.viewmodel.CommentViewModel
import com.example.easyshare.ui.viewmodel.ProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailsActivity : AppCompatActivity() {
    private val productViewModel: ProductViewModel by viewModel { parametersOf(this) }

    private val commentViewModel: CommentViewModel by viewModel()
    private lateinit var binding: ActivityProductDetailsBinding

    private lateinit var productId: String
    private lateinit var productTitle: String
    private lateinit var starButton: FloatingActionButton
    private lateinit var starFillButton: FloatingActionButton
    private var isPostStarred = false

    private lateinit var productCreatedAt: String
    private lateinit var productCreatedBy: String
    private lateinit var productDescription: String
    private lateinit var productCommentsSize: String

    private val imageBaseUrl: String = "http://10.0.2.2:3000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductInfoFromIntent()
        setProductInfoInView()
        observeProductComments()

        getMoreComments()
        publishComment()

        starButton = findViewById(R.id.favorite_action_btn)
        starFillButton = findViewById(R.id.favorite_action_fill_btn)

        this.observeIsPostStarredData()
        observeIsCommentsLoading()

        this.setStarAndStarFillButtonVisibility()
        this.listenToStarButton()
        this.listenToStarFillButton()

        this.getArticleImage()
    }

    override fun onResume() {
        super.onResume()

        this.observeIsPostStarredData()
        this.getProductInfoFromIntent()
        this.setProductInfoInView()
        this.setStarAndStarFillButtonVisibility()
    }

    private fun observeIsPostStarredData() {
        this.productViewModel.isPostStarredData.observe(this) {
            isPostStarred = it
        }
    }

    private fun setProductInfoInView() {
        binding.productDetailTitleTv.text = this.productTitle
        binding.productDetailCreatedAtTv.text = getString(R.string.created_At, this.productCreatedAt)
        binding.productCreatedByTv.text = getString(R.string.created_by, this.productCreatedBy)
        binding.productDetailsDescription.text = this.productDescription
        binding.commentsSizeTv.text = this.productCommentsSize
    }

    private fun getProductInfoFromIntent() {
        productTitle = intent.getStringExtra(PRODUCT_NAME) ?: ""
        productId = intent.getStringExtra(PRODUCT_ID) ?: ""
        productCreatedAt = intent.getStringExtra(PRODUCT_CREATED_AT) ?: ""
        productCreatedBy = intent.getStringExtra(PRODUCT_CREATED_BY) ?: ""
        productDescription = intent.getStringExtra(PRODUCT_DESCRIPTION) ?: ""
        productCommentsSize = intent.getStringExtra(PRODUCT_COMMENTS_SIZE) ?: ""

        productId?.let {
            this.commentViewModel.loadComments(it.toInt())
        }
    }

    private fun listenToStarButton() {
        starButton.setOnClickListener {
            this.productViewModel.starPost(this.productId.toInt())
            this.toggleStarAndStarFillButtons()
        }
    }

    private fun listenToStarFillButton() {
        starFillButton.setOnClickListener {
            this.productViewModel.unstarPost(this.productId.toInt())
            this.toggleStarAndStarFillButtons()
        }
    }

    private fun setStarAndStarFillButtonVisibility() {
        if (this.isPostStarred) {
            starButton.visibility = View.GONE
            starFillButton.visibility = View.VISIBLE
        } else {
            starButton.visibility = View.VISIBLE
            starFillButton.visibility = View.GONE
        }
    }

    private fun toggleStarAndStarFillButtons() {
        if (starButton.visibility == View.VISIBLE) {
            starButton.visibility = View.GONE
            starFillButton.visibility = View.VISIBLE
        } else {
            starButton.visibility = View.VISIBLE
            starFillButton.visibility = View.GONE
        }
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

    private fun publishComment() {
        binding.publishCommentBtn.setOnClickListener {
            this.addProductComment()
            binding.addCommentInput.text.clear()
        }
    }

    private fun addProductComment() {
        val comment = binding.addCommentInput.text.toString()

        if (comment.isNotEmpty()) {
            commentViewModel.addComment(productId.toInt(), comment)
        } else {
            Utils.displayToast(applicationContext, R.layout.error_toast, "Veuillez mettre un commentaire ! ", Toast.LENGTH_SHORT)
        }
    }

    private fun observeIsCommentsLoading() {
        this.commentViewModel.isLoading.observe(this) { isCommentsLoading ->
            binding.commentProgressBar.visibility = if (isCommentsLoading) View.VISIBLE else View.GONE
            binding.commentsRv.visibility = if (isCommentsLoading) View.GONE else View.VISIBLE
        }
    }

    private fun getArticleImage() {
        this.productViewModel.getProductImage(productId.toInt())

        this.productViewModel.imageData.observe(this) { response ->
            val filteredList =
                response.filter { imageData ->
                    imageData.publication_id == productId.toInt()
                }

            val imageData = filteredList[0]

            Picasso.get()
                .load("$imageBaseUrl/${imageData.lien}")
                .into(
                    binding.productDetailIm,
                    object : Callback {
                        override fun onSuccess() {
                        }

                        override fun onError(e: Exception?) {
                            // Error loading image
                            Log.e("Picasso", "Error loading image: ${e?.message}")
                        }
                    }
                )
        }
    }
}
