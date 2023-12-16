package com.example.easy_share.dummy

import com.example.easy_share.models.ProductData
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


interface FakeProductService {
    @GET("products")
    fun getFakeProducts(): Flowable<List<ProductData>>
}

