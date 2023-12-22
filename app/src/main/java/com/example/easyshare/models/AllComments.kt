package com.example.easyshare.models

data class CommentResponse(
    val data: AllComments
)

data class AllComments(
    val count: Int,
    val rows: List<Commentaire>
)
