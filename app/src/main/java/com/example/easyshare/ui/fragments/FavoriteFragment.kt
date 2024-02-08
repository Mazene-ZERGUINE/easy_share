package com.example.easyshare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.databinding.FragmentFavoriteBinding
import com.example.easyshare.models.ApiResponse
import com.example.easyshare.models.PublicationFavori
import com.example.easyshare.ui.view.adapters.FavoritePostsAdapter
import com.example.easyshare.viewmodel.FavoritePostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private val favoriteViewModel: FavoritePostViewModel by viewModel()

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoritePostRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        this.favoritePostRecyclerView = binding.favoritePostsRecyclerView

        this.observeIsLoading()
        this.observeFavoritePosts()
    }

    private fun observeIsLoading() {
        this.favoriteViewModel.isLoading.observe(viewLifecycleOwner) { isDataLoading ->
            this.binding.homePb.visibility = if (isDataLoading) View.VISIBLE else View.GONE
            this.favoritePostRecyclerView.visibility = if (isDataLoading) View.GONE else View.VISIBLE
        }
    }

    private fun observeFavoritePosts() {
        this.favoriteViewModel.favoritePosts.observe(viewLifecycleOwner) {
            this.setUpFavoritePosts(it)
        }
    }

    private fun setUpFavoritePosts(favoritePosts: ApiResponse<PublicationFavori>) {
        this.favoritePostRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        this.favoritePostRecyclerView.adapter =
            FavoritePostsAdapter(favoritePosts) { postId ->
                this.favoriteViewModel.unstarPost(postId)
            }
    }
}
