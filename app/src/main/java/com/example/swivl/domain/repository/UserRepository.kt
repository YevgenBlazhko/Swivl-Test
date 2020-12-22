package com.example.swivl.domain.repository

import com.example.swivl.data.UserDataSource
import com.example.swivl.data.remote.ApiService
import com.example.swivl.di.ApiInfo
import com.example.swivl.domain.dto.Result
import com.example.swivl.ui.main.users.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    @param:ApiInfo private val apiKey: String
) : UserDataSource {
    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val userResponse = apiService.getUsers()
            Result.Success(userResponse)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
}