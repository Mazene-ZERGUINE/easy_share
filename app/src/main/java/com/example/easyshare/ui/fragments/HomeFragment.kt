package com.example.easyshare.ui.fragments

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
import com.example.easyshare.ui.view.adapters.ProductsListAdapter
import com.example.easyshare.ui.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
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

    override fun onResume() {
        super.onResume()
        this.productViewModel.reloadPosts()
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
                }
            )
    }
}