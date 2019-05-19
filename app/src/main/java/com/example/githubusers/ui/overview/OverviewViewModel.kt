package com.example.githubusers.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.githubusers.model.User
import com.example.githubusers.remote.UserRepository
import javax.inject.Inject

class OverviewViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {
    private var repo = userRepository
    private lateinit var user: LiveData<User>

    fun loadUser(login: String): LiveData<User> {
        user = repo.fetchUser(login)
        return user
    }
}
