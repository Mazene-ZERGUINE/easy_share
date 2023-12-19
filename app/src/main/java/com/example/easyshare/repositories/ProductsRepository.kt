package com.example.easyshare.repositories

import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.services.ApiService
import io.reactivex.rxjava3.core.Single

class ProductsRepository(private val fakeProductData: ApiService) {
    fun getProducts(): Single<AllProductInfo> {
        return fakeProductData.getProducts()
    }
}
