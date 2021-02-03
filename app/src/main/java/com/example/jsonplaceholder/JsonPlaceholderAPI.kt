package com.example.jsonplaceholder

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderAPI {
    @GET("users")
    fun getUserList(): Call<List<User?>?>?
}