package com.example.easyshare.view
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.di.injectModuleDependencies
import com.example.easyshare.di.parseAndInjectConfiguration
import com.example.easyshare.models.ProductData
import com.example.easyshare.view.adapters.ProductsListAdapter
import com.example.easyshare.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var productsListRv: RecyclerView
    private lateinit var loadingProgressBar: ProgressBar
    private val productViewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.productsListRv = findViewById(R.id.product_rv)
        this.loadingProgressBar = findViewById(R.id.loadingProgressBar)

        parseAndInjectConfiguration()
        injectModuleDependencies(this)

        this.loadingProgressBar.visibility = View.VISIBLE

        this.productViewModel.completeProductData.observe(this@MainActivity) {
            this.loadingProgressBar.visibility = View.GONE
            this.setUpProductsList(it)
        }
    }

    private fun setUpProductsList(products: List<ProductData>) {
        val productsAdapter = ProductsListAdapter(products)

        productsListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        productsListRv.adapter = productsAdapter
    }
}
