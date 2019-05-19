package com.example.githubusers.ui.userlist

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.githubusers.R
import com.example.githubusers.ui.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UserListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}
