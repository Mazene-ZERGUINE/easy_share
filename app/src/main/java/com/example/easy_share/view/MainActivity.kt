package com.example.easy_share.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easy_share.R
import com.example.easy_share.di.injectModuleDependencies
import com.example.easy_share.di.parseAndInjectConfiguration
import com.example.easy_share.view.adapters.ProductsListAdapter
import com.example.easy_share.dummy.FakeProductService
import com.example.easy_share.models.ProductData
import com.example.easy_share.viewmodel.ProductViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

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

        this.productViewModel.completeProductData.observe(this@MainActivity){
            this.loadingProgressBar.visibility = View.GONE
            this.setUpProductsList(it)
        }

    }


    private fun setUpProductsList(products: List<ProductData>){
        val productsAdapter = ProductsListAdapter(products)


        productsListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        productsListRv.adapter = productsAdapter
    }
    
    
}