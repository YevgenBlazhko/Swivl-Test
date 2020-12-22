package com.example.swivl.ui.main.users

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideUserFragmentFactory(): UserFragment
}