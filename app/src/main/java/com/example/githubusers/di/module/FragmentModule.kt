package com.example.githubusers.di.module

import com.example.githubusers.ui.userlist.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeUserListFragment(): UserListFragment
}