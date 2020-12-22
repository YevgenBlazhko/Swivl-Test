package com.example.swivl.data.remote

import com.example.swivl.ui.main.users.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(ApiEndPoint.LIST_USERS)
    suspend fun getUsers(): List<User>

    @GET(ApiEndPoint.USER)
    suspend fun getUserInfo(
        @Path("user") user: String
    ): User
}