package com.example.githubusers.ui.overview

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.githubusers.Constants

import com.example.githubusers.R
import com.example.githubusers.ui.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.overview_fragment.*
import javax.inject.Inject

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
    lateinit var viewModel: OverviewViewModel
    lateinit var login: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(OverviewViewModel::class.java)


        login = arguments?.getString(Constants.KEY_USER_LOGIN)!!
        viewModel.loadUser(login).observe(this, Observer {
            with(it) {
                login_text.text = login
                name_text.text = name
                followers_text.text = getString(R.string.followers, followers)
                following_text.text = getString(R.string.following, following)
                repository_text.text = getString(R.string.repository, repos)
                gist_text.text = getString(R.string.gist, gists)

                if (bio != null) bio_text.text = bio
                if (location != null) {
                    location_frame.visibility = View.VISIBLE
                    location_text.text = location
                }
                if (company != null) {
                    company_frame.visibility = View.VISIBLE
                    company_text.text = company
                }
                if (blog != null) {
                    blog_frame.visibility = View.VISIBLE
                    blog_text.text = blog
                }

                Picasso.get().load(avatarUrl).into(profile_image)
            }
        })
    }

}
