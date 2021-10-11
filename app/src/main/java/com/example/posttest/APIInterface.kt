package com.example.posttest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
data class AddUsers(val name: String, val location: String)
interface APIInterface {
    @GET ("test")
    fun showInfo(): Call <List<UsersList?>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addInfo(@Body newUbring: AddUsers): Call<AddUsers>
}