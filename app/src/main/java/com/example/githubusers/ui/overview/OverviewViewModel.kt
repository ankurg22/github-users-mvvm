package com.example.githubusers.ui.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.model.User
import com.example.githubusers.remote.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OverviewViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {
    private var repo = userRepository
    private lateinit var disposable: Disposable

    var user = MutableLiveData<User>()

    fun loadUser(login: String) {
        disposable = repo.fetchUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe { result -> onSuccess(result) }
    }

    private fun onSuccess(result: User) {
        user.value = result
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
