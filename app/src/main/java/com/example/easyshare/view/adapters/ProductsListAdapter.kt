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
    val products: List<Data>,
    private val onProductClicked: OnProductClicked
) : RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder>() {

    companion object {
        const val PRODUCT_NAME = "product_name"
        const val PRODUCT_ID = "product_id"
        const val PRODUCT_CREATED_AT = "product_publishedAt"
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
        holder.itemView.setOnClickListener {
            onProductClicked.displayProductDetails(currentProduct)
        }
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

        }

        fun bind(productData: Data) {
            productTitleTv.text = productData.titre
            publishedAtTv.text = productData.createdAt
            profileNameTv.text = productData.utilisateur.pseudonyme
            productCommentsSize.text = productData.publicationId.toString()
        }
    }
}


interface OnProductClicked {
    fun displayProductDetails(productData: Data)
}