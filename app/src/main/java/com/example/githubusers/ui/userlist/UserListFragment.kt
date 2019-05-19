package com.example.githubusers.ui.userlist

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.utils.Constants

import com.example.githubusers.R
import com.example.githubusers.databinding.UserListFragmentBinding
import com.example.githubusers.ui.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.user_list_fragment.*
import javax.inject.Inject

/**
 * This fragment displays a list of users. Since User object is same across Github API,
 * we can re-use this fragment to display ALL, FOLLOWERS and FOLLOWING user lists.
 */
class UserListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: UserListViewModel
    private lateinit var binding: UserListFragmentBinding
    private var errorSnackbar: Snackbar? = null

    companion object {
        fun newInstance(mode: String, login: String): UserListFragment {
            val bundle = Bundle()
            bundle.putString(Constants.KEY_USER_FRAGMENT_MODE, mode)
            bundle.putString(Constants.KEY_USER_LOGIN, login)
            val fragment = UserListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.user_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.error.observe(this, Observer { error ->
            if (error != null) showError(error) else hideError()
        })

        //Check which mode fragment was started in and render accordingly
        val mode = arguments?.getString(Constants.KEY_USER_FRAGMENT_MODE)
        val login = arguments?.getString(Constants.KEY_USER_LOGIN)
        when (mode) {
            Constants.MODE_ALL -> {
                viewModel.loadUsers()
                userRecyclerView.addOnScrollListener(listener)
            }

            Constants.MODE_FOLLOWERS -> {
                viewModel.loadFollowers(login!!)
            }

            Constants.MODE_FOLLOWING -> {
                viewModel.loadFollowing(login!!)
            }
        }
    }

    private fun showError(errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    //Listener for pagination. Used only in ALL mode only
    val listener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val linearLayoutManager = userRecyclerView.layoutManager as LinearLayoutManager?
            if (linearLayoutManager!!.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2) {
                val lastUserId = viewModel.userListAdapter.getLastUserId()
                viewModel.loadUsers(lastUserId)
            }
        }
    }

}
