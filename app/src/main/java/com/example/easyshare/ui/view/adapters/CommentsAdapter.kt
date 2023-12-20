package com.example.easyshare.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.models.Commentaire

class CommentsAdapter(private val comments: List<Commentaire>) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CommentViewHolder,
        position: Int
    ) {
        holder.bind(comments[position])
    }

    override fun getItemCount() = comments.size

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var commentTv: TextView = itemView.findViewById(R.id.comment_tv)

        fun bind(commentaire: Commentaire) {
            commentTv.text = commentaire.comment
        }
    }
}
