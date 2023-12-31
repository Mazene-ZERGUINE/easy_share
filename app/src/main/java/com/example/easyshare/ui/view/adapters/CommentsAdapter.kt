package com.example.easyshare.view.adapters

import Utils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.models.Commentaire
import com.example.easyshare.utilis.DateUtils

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

    override fun getItemCount() = comments.count()

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var commentTv: TextView
        private var userName : TextView
        private var publishedAtComment: TextView

        init {
            this.commentTv = itemView.findViewById(R.id.comment_tv)
            this.userName = itemView.findViewById(R.id.user_name_comment_tv)
            this.publishedAtComment = itemView.findViewById(R.id.publisheAt_comment_tv)
        }

        fun bind(comment: Commentaire) {
            commentTv.text = comment.comment
            userName.text = comment.user.pseudonyme
            publishedAtComment.text = DateUtils.calculateTimeDifference(comment.createdAt)
        }
    }
}
