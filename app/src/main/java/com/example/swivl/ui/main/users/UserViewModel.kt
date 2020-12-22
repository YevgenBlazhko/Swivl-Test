package com.example.swivl.ui.main.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swivl.data.UserDataSource
import com.example.swivl.domain.dto.Result
import com.example.swivl.mvvm.BaseViewModel
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataSource: UserDataSource
) : BaseViewModel() {

    private val userLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val userLiveDataLiveData: LiveData<List<User>>
        get() = userLiveData

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = userDataSource.getUsers()) {
                is Result.Success<List<User>> -> {
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