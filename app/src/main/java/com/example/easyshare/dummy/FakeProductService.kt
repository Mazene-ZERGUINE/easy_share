package com.example.easyshare.dummy

import com.example.easyshare.models.ProductData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET


interface FakeProductService {
    @GET("products")
    fun getFakeProducts(): Flowable<List<ProductData>>
}

