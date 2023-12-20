package com.example.easyshare.repositories

import com.example.easyshare.models.AllComments
import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.services.ApiService
import io.reactivex.rxjava3.core.Flowable

class ProductsRepository(private val productDataService: ApiService) {
    fun getProducts(): Flowable<AllProductInfo> {
        return productDataService.getProducts()
    }

    fun getAllProductComments(): Flowable<AllComments>  {
        return productDataService.getAllProductsComments()
    }
}
