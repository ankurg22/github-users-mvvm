package com.example.githubusers.ui.userlist

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.githubusers.R
import com.example.githubusers.model.User
import com.example.githubusers.ui.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.user_list_fragment.*
import java.security.acl.Owner
import javax.inject.Inject

class UserListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: UserListViewModel
    var loading: Boolean = false
    lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)

        userRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = UserListAdapter()
        userRecyclerView.adapter = adapter
        fetchUser(0)

        userRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = userRecyclerView.layoutManager as LinearLayoutManager?
                if (!loading && linearLayoutManager!!.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2) {
                    loading = true
                    val lastUser = adapter.userList.last()
                    fetchUser(lastUser.id)
                }
            }
        })
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun fetchUser(id: Int) {
        viewModel.loadUsers(id).observe(this, Observer {
            adapter.addData(it)
            loading = false
        })

    }

}
