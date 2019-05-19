package com.example.githubusers.remote

import com.example.githubusers.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("/users")
    fun fetchUserList(@Query("since") id: Int): Call<List<User>>

    @GET("/users/{login}")
    fun fetchUser(@Path("login") login: String): Call<User>

    @GET("/users/{login}/followers")
    fun fetchFollowers(@Path("login") login: String): Call<List<User>>

    @GET("/users/{login}/following")
    fun fetchFollowing(@Path("login") login: String): Call<List<User>>
}