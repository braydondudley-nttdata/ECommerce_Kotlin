package com.ecommerce_app.repository

import com.ecommerce_app.model.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): Response<Products>
}
