package com.example.githubusers.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("bio") val bio: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("blog") val blog: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("followers") val followers: String,
    @SerializedName("following") val following: String,
    @SerializedName("public_repos") val repos: String,
    @SerializedName("public_gists") val gists: String,
    @SerializedName("url") val url: String
)