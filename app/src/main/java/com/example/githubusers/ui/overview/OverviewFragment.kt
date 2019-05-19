package com.example.githubusers.ui.overview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githubusers.R
import com.example.githubusers.databinding.OverviewFragmentBinding
import com.example.githubusers.ui.UserViewModel
import com.example.githubusers.ui.ViewModelFactory
import com.example.githubusers.utils.Constants
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.overview_fragment.*
import javax.inject.Inject

/**
 * Presents a card with basic profile of user.
 */
class OverviewFragment : Fragment() {

    companion object {
        fun newInstance(login: String): OverviewFragment {
            val bundle = Bundle()
            bundle.putString(Constants.KEY_USER_LOGIN, login)
            val fragment = OverviewFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: OverviewViewModel
    private lateinit var binding: OverviewFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.overview_fragment, container, false)
        return binding.root
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(OverviewViewModel::class.java)


        //Get login parameter form intent and set in data binding
        val login = arguments?.getString(Constants.KEY_USER_LOGIN)!!
        viewModel.loadUser(login)
        viewModel.user.observe(this, Observer {
            val userViewModel = UserViewModel()
            userViewModel.bind(it)
            binding.user = userViewModel
            Picasso.get().load(it.avatarUrl).into(profile_image)
        })
    }

}
