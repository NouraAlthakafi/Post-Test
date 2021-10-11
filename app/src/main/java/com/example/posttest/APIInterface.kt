package com.example.posttest

import retrofit2.Call
import retrofit2.http.*

data class AddUsers(val name: String, val location: String)
interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET ("/test/")
    fun showInfo(): Call <List<UsersList?>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addInfo(@Body newUbring: AddUsers): Call<AddUsers>

    @Headers("Content-Type: application/json")
    @PUT("/test/{pk}")
    fun updateInfo(@Path("pk") pk: Int, @Body updateUbring: UsersList): Call<List<UsersList>>

    @Headers("Content-Type: application/json")
    @DELETE ("/test/{pk}")
    fun deleteInfo(@Path("pk") pk: Int): Call<Void>
}