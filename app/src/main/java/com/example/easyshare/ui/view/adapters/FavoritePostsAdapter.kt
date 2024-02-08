package com.example.easyshare.ui.view.adapters

import Utils
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
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_CREATED_AT
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_CREATED_BY
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_DESCRIPTION
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_ID
import com.example.easyshare.ui.view.adapters.ProductsListAdapter.Companion.PRODUCT_NAME
import com.example.easyshare.utilis.CustomDateUtils
import com.example.easyshare.utilis.TokenManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView

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
                .inflate(R.layout.fragment_product, parent, false)

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
        private var userAvatar: CircleImageView

        init {
            productTitleTv = itemView.findViewById(R.id.productTitleTv)
            publishedAtTv = itemView.findViewById(R.id.productPublishedAt)
            profileNameTv = itemView.findViewById(R.id.profileNameTv)
            productIm = itemView.findViewById(R.id.productIm)
            starButton = itemView.findViewById(R.id.favoriteFillFab)
            userAvatar = itemView.findViewById(R.id.businessImage)

            this.listenToItemView(itemView)
            this.listenToStarButton()

            if (itemCount >= 1) {
                Utils.loadRandomUserAvatar(itemView)
            }
        }

        fun bind(currentFavoritePost: PublicationFavori) {
            productTitleTv.text = currentFavoritePost.publication.titre
            publishedAtTv.text = CustomDateUtils.calculateTimeDifference(currentFavoritePost.publication.createdAt)
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
                        putExtra(PRODUCT_CREATED_BY, TokenManager.getPseudonymeFromToken())
                        putExtra(PRODUCT_CREATED_AT, CustomDateUtils.formatReadableDate(favoritePost.createdAt))
                        putExtra(PRODUCT_DESCRIPTION, favoritePost.publication.description)
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
