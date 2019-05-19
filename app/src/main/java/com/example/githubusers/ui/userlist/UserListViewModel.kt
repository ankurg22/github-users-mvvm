package com.example.githubusers.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.githubusers.model.User
import com.example.githubusers.remote.UserRepository
import javax.inject.Inject

class UserListViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {
    private var repo = userRepository
    private lateinit var userList: LiveData<List<User>>

    fun loadUsers(): LiveData<List<User>> {
        userList = repo.fetchUserList(0)
        return userList
    }
}
