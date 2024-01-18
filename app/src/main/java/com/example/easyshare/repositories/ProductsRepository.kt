package com.example.easyshare.repositories

import android.util.Log
import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.models.CommentResponse
import com.example.easyshare.models.NewProductRequest
import com.example.easyshare.services.ApiService
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class ProductsRepository(private val productDataService: ApiService) {
    fun getProducts(): Single<AllProductInfo> {
        return productDataService.getProducts()
    }

    fun getAllProductComments(publicationId: Int): Flowable<CommentResponse> {
        return productDataService.getAllProductsComments(publicationId)
    }

    fun addProduct(payload: NewProductRequest): Flowable<Unit>  {
        Log.d("payLoad", payload.toString())
        return productDataService.addNewProduct(payload)
    }
}
