package com.example.githubusers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubusers.utils.Constants
import com.example.githubusers.R
import com.example.githubusers.databinding.ActivityProfileBinding
import com.example.githubusers.ui.overview.OverviewFragment
import com.example.githubusers.ui.userlist.UserListFragment
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        supportActionBar?.elevation = 0f

        var userLogin: String = ""
        if (intent != null) {
            userLogin = intent.getStringExtra(Constants.KEY_USER_LOGIN)
        }

        val adapter = ProfilePagerAdapter(supportFragmentManager)
        adapter.addPage(OverviewFragment.newInstance(userLogin), getString(R.string.overview))
        adapter.addPage(UserListFragment.newInstance(Constants.MODE_FOLLOWING, userLogin), getString(R.string.following_title))
        adapter.addPage(UserListFragment.newInstance(Constants.MODE_FOLLOWERS, userLogin), getString(R.string.followers_title))
        binding.vpProfile.adapter = adapter

        tabLayout.setupWithViewPager(vp_profile)
    }

    class ProfilePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        val fragmentList: ArrayList<Fragment> = ArrayList()
        val titleList: ArrayList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList.get(position)
        }

        fun addPage(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

    }
}
