package com.example.easyshare.models

data class DataProperty<T>(
    val count: Int,
    val rows: List<T>
)

data class IsStarredPost(
    val starred: Boolean
)

data class GenericApiResponse<T>(
    val data: T
)

data class ApiResponse<T>(
    val data: DataProperty<T>
)
