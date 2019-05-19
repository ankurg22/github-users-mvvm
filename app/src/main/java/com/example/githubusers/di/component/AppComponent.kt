package com.example.githubusers.di.component

import android.app.Application
import com.example.githubusers.App
import com.example.githubusers.di.module.*
import com.example.githubusers.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ImageModule::class,
        NetworkModule::class, ActivityModule::class,
        FragmentModule::class, ViewModelModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}