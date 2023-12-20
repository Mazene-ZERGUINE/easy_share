package com.example.easyshare.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.models.Data
import com.example.easyshare.view.ProductDetailsActivity

class ProductsListAdapter(
    val products: List<Data>
) : RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder>() {
    companion object {
        const val PRODUCT_ID = "com.example.easyshare.fragments.idProduct"
        const val PRODUCT_NAME = "com.example.easyshare.fragments.title"
        const val PRODUCT_COMMENTS = "com.example.easyshare.fragments.COMMENTS"
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
        holder.bind(currentProduct)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var productTitleTv: TextView
        private var publishedAtTv: TextView
        private var profileNameTv: TextView
        private var productIm: ImageView
        private var productCommentsSize: TextView

        init {
            productTitleTv = itemView.findViewById(R.id.productTitleTv)
            publishedAtTv = itemView.findViewById(R.id.productPublishedAt)
            profileNameTv = itemView.findViewById(R.id.profileNameTv)
            productIm = itemView.findViewById(R.id.productIm)
            productCommentsSize = itemView.findViewById(R.id.comments_size_tv)

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
        }

        fun bind(productData: Data) {
            productTitleTv.text = productData.titre
            publishedAtTv.text = productData.createdAt
            profileNameTv.text = productData.utilisateur.pseudonyme
            productCommentsSize.text = productData.publicationId.toString()
        }
    }
}
