package com.example.easyshare.repositories

import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.models.CommentRequest
import com.example.easyshare.models.CommentResponse
import com.example.easyshare.models.ImageData
import com.example.easyshare.services.ApiService
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProductsRepository(private val productDataService: ApiService) {
    fun getProducts(): Single<AllProductInfo> {
        return productDataService.getProducts()
    }

    fun getAllProductComments(publicationId: Int): Flowable<CommentResponse> {
        return productDataService.getAllProductsComments(publicationId)
    }

    fun addNewProduct(
        titleRequestBody: RequestBody,
        descriptionRequestBody: RequestBody,
        imagePart: MultipartBody.Part?,
        userIdRequestBody: RequestBody
    ): Flowable<Unit> {
           return this.productDataService.addNewProduct(titleRequestBody, descriptionRequestBody, imagePart!!, userIdRequestBody)
    }

    fun addComment(
        publicationId: Int,
        payload: CommentRequest
    ): Flowable<Unit> {
        return productDataService.addCommentToProduct(publicationId, payload)
    }

    fun getProductImage(imageId: Int): Flowable<List<ImageData>> {
        return  productDataService.getImage()
    }
}
