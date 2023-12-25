package com.example.easyshare.models

data class DataProperty<T>(
    val count: Int,
    val rows: List<T>
)

data class ApiResponse<T>(
    val data: DataProperty<T>
)
