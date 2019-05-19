package com.example.githubusers.remote

import com.example.githubusers.model.User
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(apiClient: ApiClient) {
    val api = apiClient

    fun fetchUserList(sinceId: Int): Observable<List<User>> {
        return api.fetchUserList(sinceId)
    }

    fun fetchUser(login: String): Observable<User> {
        return api.fetchUser(login)
    }

    fun fetchFollowers(login: String): Observable<List<User>> {
        return api.fetchFollowers(login)
    }

    fun fetchFollowing(login: String): Observable<List<User>> {
        return api.fetchFollowing(login)
    }
}