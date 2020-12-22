package com.example.swivl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.swivl.data.UserDataSource
import com.example.swivl.data.UserDetailsDataSource
import com.example.swivl.ui.main.MainViewModel
import com.example.swivl.ui.main.users.UserViewModel
import com.example.swivl.ui.main.users_details.UserDetailsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userDetailsDataSource: UserDetailsDataSource
) : NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel() as T
            }
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(userDataSource) as T
            }
            modelClass.isAssignableFrom(UserDetailsViewModel::class.java) -> {
                UserDetailsViewModel(userDetailsDataSource) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}