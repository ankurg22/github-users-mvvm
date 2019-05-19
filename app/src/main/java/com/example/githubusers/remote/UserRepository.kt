package com.example.githubusers.remote

import androidx.lifecycle.MutableLiveData
import com.example.githubusers.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(apiClient: ApiClient) {
    val api = apiClient

    fun fetchUserList(sinceId: Int): MutableLiveData<List<User>> {
        val data = MutableLiveData<List<User>>()
        api.fetchUserList(sinceId).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }
        })
        return data
    }

    fun fetchUser(login: String): MutableLiveData<User> {
        val data = MutableLiveData<User>()
        api.fetchUser(login).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
            }

        })
        return data
    }
}