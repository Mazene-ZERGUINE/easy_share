package com.example.easyshare.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.models.ApiResponse
import com.example.easyshare.models.PublicationFavori
import com.example.easyshare.ui.view.ProductDetailsActivity
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_ID
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME
import com.example.easyshare.utilis.TokenManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FavoritePostsAdapter(
    private val favoritePosts: ApiResponse<PublicationFavori>,
    private val onUnstar: (Int) -> Unit
) : RecyclerView.Adapter<FavoritePostsAdapter.FavoritePostsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritePostsViewHolder {
        val favoritePostView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.fragment_favorite_posts, parent, false)

        return FavoritePostsViewHolder(favoritePostView)
    }

    override fun getItemCount() = favoritePosts.data.count

    override fun onBindViewHolder(
        holder: FavoritePostsViewHolder,
        position: Int
    ) {
        val currentFavoritePost = favoritePosts.data.rows[position]
        holder.bind(currentFavoritePost)
    }

    inner class FavoritePostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var productTitleTv: TextView
        private var publishedAtTv: TextView
        private var profileNameTv: TextView
        private var productIm: ImageView
        private var starButton: FloatingActionButton

        init {
            productTitleTv = itemView.findViewById(R.id.productTitleTv)
            publishedAtTv = itemView.findViewById(R.id.productPublishedAt)
            profileNameTv = itemView.findViewById(R.id.profileNameTv)
            productIm = itemView.findViewById(R.id.productIm)
            starButton = itemView.findViewById(R.id.favoriteFab)

            this.listenToItemView(itemView)
            this.listenToStarButton()
        }

        fun bind(currentFavoritePost: PublicationFavori) {
            productTitleTv.text = currentFavoritePost?.publication?.titre
            publishedAtTv.text = currentFavoritePost?.publication?.createdAt
            profileNameTv.text = TokenManager.getPseudonymeFromToken()
        }

        private fun listenToItemView(itemView: View) {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) {
                    return@setOnClickListener
                }

                val favoritePost = favoritePosts.data.rows[adapterPosition]
                val context = itemView.context
                val intent =
                    Intent(context, ProductDetailsActivity::class.java).apply {
                        putExtra(PRODUCT_ID, favoritePost.publicationId.toString())
                        putExtra(PRODUCT_NAME, favoritePost.publication.titre)
                    }

                context.startActivity(intent)
            }
        }

        private fun listenToStarButton() {
            starButton.setOnClickListener {
                val favoritePostId = favoritePosts.data.rows[adapterPosition].publicationId

                onUnstar(favoritePostId)
            }
        }
    }
}