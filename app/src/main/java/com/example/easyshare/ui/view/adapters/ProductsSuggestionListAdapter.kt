package com.example.easyshare.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.models.Data
import com.example.easyshare.utilis.CustomDateUtils

class ProductsSuggestionListAdapter(val productsSuggestion: List<Data>) :
    RecyclerView.Adapter<ProductsSuggestionListAdapter.ProductSuggestionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsSuggestionListAdapter.ProductSuggestionViewHolder {
        val productSuggestionView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.fragment_suggestion, parent, false)

        return ProductSuggestionViewHolder(productSuggestionView)
    }

    override fun onBindViewHolder(
        holder: ProductsSuggestionListAdapter.ProductSuggestionViewHolder,
        position: Int
    ) {
        val currentProduct = productsSuggestion[position]

        holder.bind(currentProduct)
    }

    override fun getItemCount() = productsSuggestion.count()

    inner class ProductSuggestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var productTitleTv: TextView
        private var publishedAtTv: TextView
        private var profileNameTv: TextView
        private var productIm: ImageView

        init {
            productTitleTv = itemView.findViewById(R.id.productTitleTv)
            publishedAtTv = itemView.findViewById(R.id.suggestPublishebAt)
            profileNameTv = itemView.findViewById(R.id.profileNameTv)
            productIm = itemView.findViewById(R.id.product_suggestion_im)
        }

        fun bind(productData: Data) {
            productTitleTv.text = productData.titre
            publishedAtTv.text = CustomDateUtils.calculateTimeDifference(productData.createdAt)
            profileNameTv.text = productData.utilisateur.pseudonyme
        }
    }
}
