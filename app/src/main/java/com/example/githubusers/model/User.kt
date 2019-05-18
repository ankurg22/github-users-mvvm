package com.example.githubusers.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("followers_url") val followersUrl: String,
    @SerializedName("following_url") val followingUrl: String,
    @SerializedName("repos_url") val reposUrl: String
)