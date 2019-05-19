package com.example.githubusers.ui.userlist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.githubusers.R
import com.example.githubusers.model.User
import com.example.githubusers.remote.UserRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.internal.util.NotificationLite.disposable


class UserListViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {
    private var repo = userRepository
    private lateinit var disposable: Disposable

    val userListAdapter = UserListAdapter()
    val loading: MutableLiveData<Int> = MutableLiveData()
    val error: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadUsers() }


    fun loadUsers(sinceId: Int = 0) {
        disposable = repo.fetchUserList(sinceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(
                { result -> onSuccess(result) }
            )
    }

    fun loadFollowers(login: String) {
        disposable = repo.fetchFollowers(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(
                { result -> onSuccess(result) }
            )
    }

    fun loadFollowing(login: String) {
        disposable = repo.fetchFollowing(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe { result -> onSuccess(result) }
    }

    private fun onStart() {
        loading.value = View.VISIBLE
        error.value = null
    }

    private fun onFinish() {
        loading.value = View.GONE
    }

    private fun onSuccess(result: List<User>) {
        userListAdapter.addData(result)
    }

    private fun onError() {
        error.value = R.string.error
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
