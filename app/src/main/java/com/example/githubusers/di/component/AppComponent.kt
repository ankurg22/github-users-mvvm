package com.example.githubusers.di.component

import android.app.Application
import com.example.githubusers.App
import com.example.githubusers.di.module.ActivityModule
import com.example.githubusers.di.module.ImageModule
import com.example.githubusers.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ImageModule::class, NetworkModule::class, ActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}