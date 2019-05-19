package com.example.githubusers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubusers.Constants
import com.example.githubusers.R
import com.example.githubusers.ui.overview.OverviewFragment
import com.example.githubusers.ui.userlist.UserListFragment
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var userLogin: String = ""
        if (intent != null) {
            userLogin = intent.getStringExtra(Constants.KEY_USER_LOGIN)
        }

        val adapter = ProfilePagerAdapter(supportFragmentManager)
        adapter.addPage(OverviewFragment.newInstance(userLogin), "Overview")
        adapter.addPage(UserListFragment.newInstance(Constants.MODE_FOLLOWING, userLogin), "Following")
        adapter.addPage(UserListFragment.newInstance(Constants.MODE_FOLLOWERS, userLogin), "Followers")
        vp_profile.adapter = adapter

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
