package com.example.swivl.domain.repository

import com.example.swivl.data.UserDetailsDataSource
import com.example.swivl.data.remote.ApiService
import com.example.swivl.di.ApiInfo
import com.example.swivl.domain.dto.Result
import com.example.swivl.ui.main.users.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailsRepository @Inject constructor(
    private val apiService: ApiService,
    @param:ApiInfo private val apiKey: String
) : UserDetailsDataSource {

    override suspend fun getUserInfo(user: String): Result<User> {
        return try {
            val userResponse = apiService.getUserInfo(user)
            Result.Success(userResponse)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
}