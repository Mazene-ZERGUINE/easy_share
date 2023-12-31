package com.example.easyshare.ui.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.models.Data
import com.example.easyshare.ui.view.ProductDetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsListAdapter(
    val products: List<Data>,
<<<<<<< HEAD:app/src/main/java/com/example/easyshare/ui/view/adapters/ProductsListAdapter.kt
    private val isPostStarred: (String) -> Boolean?,
    private val onStar: (Int) -> Unit,
    private val onUnstar: (Int) -> Unit
=======
    private val onProductClicked: OnProductClicked
>>>>>>> df667ba ([productsDetails]: refactor onClickProduct):app/src/main/java/com/example/easyshare/view/adapters/ProductsListAdapter.kt
) : RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder>() {

    companion object {
        const val PRODUCT_NAME = "product_name"
        const val PRODUCT_ID = "product_id"
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val productView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.fragment_product, parent, false)

        return ProductViewHolder(productView)
    }

    override fun getItemCount() = products.count()

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val currentProduct = products[position]
<<<<<<< HEAD:app/src/main/java/com/example/easyshare/ui/view/adapters/ProductsListAdapter.kt

=======
        holder.itemView.setOnClickListener {
            onProductClicked.displayProductDetails(currentProduct)
        }
>>>>>>> df667ba ([productsDetails]: refactor onClickProduct):app/src/main/java/com/example/easyshare/view/adapters/ProductsListAdapter.kt
        holder.bind(currentProduct)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var productTitleTv: TextView
        private var publishedAtTv: TextView
        private var profileNameTv: TextView
        private var productIm: ImageView
        private var productCommentsSize: TextView
        private var starButton: FloatingActionButton
        private var starFillButton: FloatingActionButton

        init {
            productTitleTv = itemView.findViewById(R.id.productTitleTv)
            publishedAtTv = itemView.findViewById(R.id.productPublishedAt)
            profileNameTv = itemView.findViewById(R.id.profileNameTv)
            productIm = itemView.findViewById(R.id.productIm)
            productCommentsSize = itemView.findViewById(R.id.comments_size_tv)
            starButton = itemView.findViewById(R.id.favoriteFab)
            starFillButton = itemView.findViewById(R.id.favoriteFillFab)

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val product = products[adapterPosition]

                    val context = itemView.context
                    val intent =
                        Intent(context, ProductDetailsActivity::class.java).apply {
                            putExtra(PRODUCT_ID, product.publicationId.toString())
                            putExtra(PRODUCT_NAME, product.titre)
                        }
                    context.startActivity(intent)
                }
            }

            this.listenToStarButton()
            this.listenToStarFillButton()
        }

        fun bind(productData: Data) {
            productTitleTv.text = productData.titre
            publishedAtTv.text = productData.createdAt
            profileNameTv.text = productData.utilisateur.pseudonyme
            productCommentsSize.text = productData.comments[0].comment

            if (adapterPosition != RecyclerView.NO_POSITION) {
                this.setStarAndStarFillButtonVisibility()
            }
        }

        private fun setStarAndStarFillButtonVisibility() {
            val post = products[adapterPosition]

            if (isPostStarred(post.publicationId.toString()) == true) {
                starButton.visibility = View.GONE
                starFillButton.visibility = View.VISIBLE
            } else {
                starButton.visibility = View.VISIBLE
                starFillButton.visibility = View.GONE
            }
        }

        private fun listenToStarButton() {
            starButton.setOnClickListener {
                onStar(products[adapterPosition].publicationId)
                this.toggleStarAndStarFillButtons()
            }
        }

        private fun listenToStarFillButton() {
            starFillButton.setOnClickListener {
                onUnstar(products[adapterPosition].publicationId)
                this.toggleStarAndStarFillButtons()
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
            productCommentsSize.text = productData.publicationId.toString()
        }
    }
}


interface OnProductClicked {
    fun displayProductDetails(productData: Data)
}