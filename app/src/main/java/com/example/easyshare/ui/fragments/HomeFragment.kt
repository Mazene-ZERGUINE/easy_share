package com.example.easyshare.ui.fragments

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
import com.example.easyshare.ui.view.ProductDetailsActivity
import com.example.easyshare.ui.view.adapters.OnProductClicked
import com.example.easyshare.ui.view.adapters.ProductsListAdapter
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_COMMENTS_SIZE
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_CREATED_AT
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_CREATED_BY
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_DESCRIPTION
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_ID
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME
import com.example.easyshare.ui.view.adapters.ProductsSuggestionListAdapter
import com.example.easyshare.ui.viewmodel.ProductViewModel
import com.example.easyshare.utilis.CustomDateUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment(), OnProductClicked {
    private val productViewModel: ProductViewModel by viewModel { parametersOf(requireContext()) }

    private lateinit var binding: FragmentHomeBinding

    private lateinit var productsListRv: RecyclerView

    private lateinit var productsSuggestionListRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        this.productsSuggestionListRv = binding.productsSugestionRv

        this.observeIsLoading()
        this.observeCompleteProductData()
    }

    override fun onResume() {
        super.onResume()
        this.productViewModel.reloadPosts()
    }

    private fun observeCompleteProductData() {
        this.productViewModel.completeProductData.observe(viewLifecycleOwner) {
            this.setUpProductsList(it)
        }
        this.productViewModel.userProductData.observe(viewLifecycleOwner) {
            this.setUpProductSuggestionList(it)
        }
    }

    private fun observeIsLoading() {
        this.productViewModel.isLoading.observe(viewLifecycleOwner) { isDataLoading ->
            this.binding.homePb.visibility = if (isDataLoading) View.VISIBLE else View.GONE
            this.productsListRv.visibility = if (isDataLoading) View.GONE else View.VISIBLE
            this.productsSuggestionListRv.visibility = if (isDataLoading) View.GONE else View.VISIBLE
        }
    }

    private fun setUpProductsList(products: List<Data>) {
        productsListRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        productsListRv.adapter =
            ProductsListAdapter(
                products,
                isPostStarred = { postId ->
                    this.productViewModel.isPostStarred(postId)
                },
                onStar = { postId ->
                    this.productViewModel.starPost(postId)
                },
                onUnstar = { postId ->
                    this.productViewModel.unstarPost(postId)
                },
                this
            )
    }

    private fun setUpProductSuggestionList(productsSuggestion: List<Data>) {
        productsSuggestionListRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        productsSuggestionListRv.adapter = ProductsSuggestionListAdapter(productsSuggestion)
    }

    override fun displayProductDetails(productData: Data) {
        val intent = Intent(context, ProductDetailsActivity::class.java)
        intent.putExtra(PRODUCT_ID, productData.publicationId.toString())
        intent.putExtra(PRODUCT_NAME, productData.titre)
        intent.putExtra(PRODUCT_CREATED_AT, CustomDateUtils.formatReadableDate(productData.createdAt))
        intent.putExtra(PRODUCT_CREATED_BY, productData.utilisateur.pseudonyme)
        intent.putExtra(PRODUCT_DESCRIPTION, productData.description)
        intent.putExtra(PRODUCT_COMMENTS_SIZE, productData.comments.size.toString())
        startActivity(intent)
    }
}
