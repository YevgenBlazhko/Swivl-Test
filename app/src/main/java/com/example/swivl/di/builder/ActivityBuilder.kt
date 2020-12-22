package com.example.swivl.di.builder

import com.example.swivl.ui.main.MainActivity
import com.example.swivl.ui.main.users.UserFragmentProvider
import com.example.swivl.ui.main.users_details.UserDetailsFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(
        modules = [
            UserFragmentProvider::class,
            UserDetailsFragmentProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}