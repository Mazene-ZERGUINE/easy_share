package com.example.easyshare.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.databinding.FragmentHomeBinding
import com.example.easyshare.di.injectModuleDependencies
import com.example.easyshare.di.parseAndInjectConfiguration
import com.example.easyshare.models.Data
import com.example.easyshare.view.ProductDetailsActivity
import com.example.easyshare.view.adapters.OnProductClicked
import com.example.easyshare.view.adapters.ProductsListAdapter
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_CREATED_AT
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_ID
import com.example.easyshare.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME
import com.example.easyshare.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), OnProductClicked {
    private val productViewModel: ProductViewModel by viewModel()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var productsListRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        parseAndInjectConfiguration()
        injectModuleDependencies(requireContext())

        this.productsListRv = binding.productsRv

        this.productViewModel.isLoading.observe(viewLifecycleOwner) { isDataLoading ->
            binding.homePb.visibility = if (isDataLoading) View.VISIBLE else View.GONE
        }

        this.productViewModel.completeProductData.observe(viewLifecycleOwner) {
            this.setUpProductsList(it)
        }
    }

    private fun setUpProductsList(products: List<Data>) {
        val productsAdapter = ProductsListAdapter(products, this)
        productsListRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        productsListRv.adapter = productsAdapter
    }

    override fun displayProductDetails(productData: Data) {
        val intent = Intent(context, ProductDetailsActivity::class.java)
        intent.putExtra(PRODUCT_ID, productData.publicationId.toString())
        intent.putExtra(PRODUCT_NAME, productData.titre)
        intent.putExtra(PRODUCT_CREATED_AT, productData.createdAt)
        startActivity(intent)
    }

}
