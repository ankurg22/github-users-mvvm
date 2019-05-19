package com.example.githubusers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubusers.Constants
import com.example.githubusers.R
import com.example.githubusers.ui.userlist.UserListFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(activity_content.id, UserListFragment.newInstance(Constants.MODE_ALL, ""))
                .commit()
    }
}
