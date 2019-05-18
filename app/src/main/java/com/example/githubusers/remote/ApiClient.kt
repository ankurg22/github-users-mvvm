package com.example.githubusers.remote

import androidx.lifecycle.LiveData
import com.example.githubusers.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("/users")
    fun fetchUserList(@Query("since") id: Int): Call<List<User>>
}