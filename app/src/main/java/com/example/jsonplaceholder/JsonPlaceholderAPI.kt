package com.example.jsonplaceholder

import com.example.jsonplaceholder.dataClasses.Album
import com.example.jsonplaceholder.dataClasses.Post
import com.example.jsonplaceholder.dataClasses.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface JsonPlaceholderAPI {
    @GET("users")
    fun getUserList(): Call<List<User?>?>?

    @GET("albums")
    fun getUserAlbums(): Call<List<Album?>?>?

    @GET("users/{userId}/posts")
    fun getUserPosts(
        @Path("userId") userId: Int
    ): Call<List<Post?>?>?

    @GET("/posts/{id}")
    fun getPostWithID(
        @Path("id") id: Int
    ): Call<Post?>?
}