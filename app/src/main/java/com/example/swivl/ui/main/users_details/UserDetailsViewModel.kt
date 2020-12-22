package com.example.swivl.ui.main.users_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swivl.data.UserDetailsDataSource
import com.example.swivl.domain.dto.Result
import com.example.swivl.mvvm.BaseViewModel
import com.example.swivl.ui.main.users.User
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val userDetailsDataSource: UserDetailsDataSource
) : BaseViewModel() {

    private val userLiveData: MutableLiveData<User> = MutableLiveData()
    val userLiveDataLiveData: LiveData<User>
        get() = userLiveData

    fun fetchUsers(user: String) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = userDetailsDataSource.getUserInfo(user)) {
                is Result.Success<User> -> {
                    userLiveData.value = result.data
                    isLoading.value = false
                }
                is Result.Error -> {
                    isLoading.value = false
                    showToast.value = result.message
                }
            }
        }
    }

}