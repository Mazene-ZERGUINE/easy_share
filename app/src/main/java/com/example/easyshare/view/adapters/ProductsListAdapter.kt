package com.example.easyshare.view.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyshare.R
import com.example.easyshare.models.ProductData
import com.example.easyshare.view.ProductDetailsActivity

class ProductsListAdapter(
    val products: List<ProductData>
) : RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder>() {
    companion object {
        const val PRODUCT_ID = "com.example.easyshare.fragments.idProduct"
        const val PRODUCT_NAME = "com.example.easyshare.fragments.title"
        const val PRODUCT_IMAGE = "com.example.easyshare.fragments.imageUrl"
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

        init {
            productTitleTv = itemView.findViewById(R.id.productTitleTv)
            publishedAtTv = itemView.findViewById(R.id.productPublishedAt)
            profileNameTv = itemView.findViewById(R.id.profileNameTv)
            productIm = itemView.findViewById(R.id.productIm)

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val product = products[adapterPosition]
                    Log.d("getFakeProducts", product.toString())
                    val context = itemView.context
                    val intent =
                        Intent(context, ProductDetailsActivity::class.java).apply {
                            putExtra(PRODUCT_ID, product.idProduct.toString())
                            putExtra(PRODUCT_NAME, product.title)
                            putExtra(PRODUCT_IMAGE, product.imageUrl)
                        }
                    context.startActivity(intent)
                }
            }
        }

        fun bind(productData: ProductData) {
            productTitleTv.text = productData.title
            publishedAtTv.text = productData.publishedAt
            profileNameTv.text = productData.author

            Glide.with(itemView.context)
                .load(productData.imageUrl)
                .into(productIm)
        }
    }
}
