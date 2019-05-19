package com.example.githubusers.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubusers.di.key.ViewModelKey
import com.example.githubusers.ui.ViewModelFactory
import com.example.githubusers.ui.userlist.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserListViewModel(viewModel: UserListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}