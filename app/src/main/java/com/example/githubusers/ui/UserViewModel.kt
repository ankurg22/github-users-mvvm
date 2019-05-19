package com.example.githubusers.ui

import androidx.lifecycle.MutableLiveData
import com.example.githubusers.model.User

/**
 * ViewModel for User entity used in User list item and Overview
 */
class UserViewModel {
    val login = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val avatar = MutableLiveData<String>()
    val bio = MutableLiveData<String>()
    val company = MutableLiveData<String>()
    val blog = MutableLiveData<String>()
    val location = MutableLiveData<String>()
    val followers = MutableLiveData<String>()
    val following = MutableLiveData<String>()
    val repos = MutableLiveData<String>()
    val gists = MutableLiveData<String>()

    fun bind(user: User) {
        login.value = user.login
        name.value = user.name
        avatar.value = user.avatarUrl
        bio.value = user.bio
        company.value = user.company
        blog.value = user.blog
        location.value = user.location
        followers.value = user.followers
        following.value = user.following
        repos.value = user.repos
        gists.value = user.gists
    }
}