package com.emma.pruebas.api

import com.emma.pruebas.constant.ApiCredentials
import com.emma.pruebas.data.Audit
import com.emma.pruebas.data.ProductsResponse
import com.emma.pruebas.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiEndPoints {

    @GET(ApiCredentials.PRODUCTS)
    suspend fun getProducts() : Response<ProductsResponse>

    @GET(ApiCredentials.AUDIT)
    suspend fun getData() : Response<Audit>

    @GET(ApiCredentials.USER)
    suspend fun getUser() : Response<UserResponse>
}