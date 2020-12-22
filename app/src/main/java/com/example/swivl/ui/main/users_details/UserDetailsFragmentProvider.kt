package com.example.swivl.ui.main.users_details

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserDetailsFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideArticleDetailsFragmentFactory(): UserDetailsFragment
}