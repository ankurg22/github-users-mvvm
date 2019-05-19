package com.example.githubusers.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.githubusers.model.User
import com.example.githubusers.remote.UserRepository
import javax.inject.Inject

class UserListViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {
    private var repo = userRepository
    private lateinit var userList: LiveData<List<User>>



    fun loadUsers(sinceId: Int): LiveData<List<User>> {
        userList = repo.fetchUserList(sinceId)
        return userList
    }

    fun loadFollowers(login:String):LiveData<List<User>>{
        userList = repo.fetchFollowers(login)
        return userList
    }

    fun loadFollowing(login:String):LiveData<List<User>>{
        userList = repo.fetchFollowing(login)
        return userList
    }
}
