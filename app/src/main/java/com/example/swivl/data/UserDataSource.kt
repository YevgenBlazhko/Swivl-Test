package com.example.swivl.data

import com.example.swivl.domain.dto.Result
import com.example.swivl.ui.main.users.User

interface UserDataSource {
    suspend fun getUsers(): Result<List<User>>
}