package com.emma.pruebas.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results") val results : List<Results>,
    @SerializedName("info") val info : Info
)

sealed class User {

    data class Master(
        val data: UserResponse
    ) : User()

    data class Error(
        val error: String
    ) : User()

    data class Exception(
        val exception: String
    ) : User()

    object Empty : User()
}

data class Results (
    @SerializedName("gender") val gender : String,
    @SerializedName("name") val name : Name,
    @SerializedName("location") val location : Location,
    @SerializedName("email") val email : String,
    @SerializedName("login") val login : Login,
    @SerializedName("dob") val dob : Dob,
    @SerializedName("registered") val registered : Registered,
    @SerializedName("phone") val phone : String,
    @SerializedName("cell") val cell : String,
    @SerializedName("id") val id : Id,
    @SerializedName("picture") val picture : Picture,
    @SerializedName("nat") val nat : String
)

data class Coordinates (
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
)

data class Dob (
    @SerializedName("date") val date : String,
    @SerializedName("age") val age : Int
)

data class Id (

    @SerializedName("name") val name : String,
    @SerializedName("value") val value : String
)

data class Info (
    @SerializedName("seed") val seed : String,
    @SerializedName("results") val results : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("version") val version : Double
)

data class Location (
    @SerializedName("street") val street : Street,
    @SerializedName("city") val city : String,
    @SerializedName("state") val state : String,
    @SerializedName("country") val country : String,
    @SerializedName("postcode") val postcode : String,
    @SerializedName("coordinates") val coordinates : Coordinates,
    @SerializedName("timezone") val timezone : Timezone
)

data class Login (
    @SerializedName("uuid") val uuid : String,
    @SerializedName("username") val username : String,
    @SerializedName("password") val password : String,
    @SerializedName("salt") val salt : String,
    @SerializedName("md5") val md5 : String,
    @SerializedName("sha1") val sha1 : String,
    @SerializedName("sha256") val sha256 : String
)

data class Name (
    @SerializedName("title") val title : String,
    @SerializedName("first") val first : String,
    @SerializedName("last") val last : String
)

data class Picture (
    @SerializedName("large") val large : String,
    @SerializedName("medium") val medium : String,
    @SerializedName("thumbnail") val thumbnail : String
)

data class Registered (
    @SerializedName("date") val date : String,
    @SerializedName("age") val age : Int
)

data class Street (
    @SerializedName("number") val number : Int,
    @SerializedName("name") val name : String
)

data class Timezone (
    @SerializedName("offset") val offset : String,
    @SerializedName("description") val description : String
)