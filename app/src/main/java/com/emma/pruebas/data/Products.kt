package com.emma.pruebas.data

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products") val products : List<Pro>,
    @SerializedName("total") val total : Int,
    @SerializedName("skip") val skip : Int,
    @SerializedName("limit") val limit : Int
)

data class Pro (
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("price") val price : Int,
    @SerializedName("discountPercentage") val discountPercentage : Double,
    @SerializedName("rating") val rating : Double,
    @SerializedName("stock") val stock : Int,
    @SerializedName("brand") val brand : String,
    @SerializedName("category") val category : String,
    @SerializedName("thumbnail") val thumbnail : String,
    @SerializedName("images") val images : List<String>
)

sealed class Products {

    data class Master(
        val data: ProductsResponse
    ) : Products()

    data class Error(
        val error: String
    ) : Products()

    data class Exception(
        val exception: String
    ) : Products()

    object Empty : Products()

}