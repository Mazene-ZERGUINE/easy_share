package com.example.easyshare.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.easyshare.R
import com.example.easyshare.databinding.ActivityProductDetailsBinding
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_ID
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME
import com.example.easyshare.ui.viewmodel.ProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsActivity : AppCompatActivity() {
    private val productViewModel: ProductViewModel by viewModel()
    private lateinit var binding: ActivityProductDetailsBinding

    private lateinit var productId: String
    private lateinit var productTitle: String
    private lateinit var starButton: FloatingActionButton
    private lateinit var starFillButton: FloatingActionButton
    private var isPostStarred = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductInfoFromIntent()
        setProductInfoInView()

        starButton = findViewById(R.id.favorite_action_btn)
        starFillButton = findViewById(R.id.favorite_action_fill_btn)

        this.observeIsPostStarredData()

        this.setStarAndStarFillButtonVisibility()
        this.listenToStarButton()
        this.listenToStarFillButton()
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
    }

    private fun getProductInfoFromIntent() {
        productId = intent.getStringExtra(PRODUCT_ID) ?: ""
        productTitle = intent.getStringExtra(PRODUCT_NAME) ?: ""
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
        if (this.isPostStarred == true) {
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
}
