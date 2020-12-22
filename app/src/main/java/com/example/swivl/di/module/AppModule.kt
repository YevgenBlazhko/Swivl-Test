package com.example.swivl.di.module

import android.app.Application
import android.content.Context
import com.example.swivl.BuildConfig
import com.example.swivl.data.UserDataSource
import com.example.swivl.data.UserDetailsDataSource
import com.example.swivl.data.remote.ApiService
import com.example.swivl.di.ApiInfo
import com.example.swivl.domain.repository.UserDetailsRepository
import com.example.swivl.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @ApiInfo
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleDataSource(UserRepository: UserRepository): UserDataSource {
        return UserRepository
    }

    @Provides
    @Singleton
    fun provideArticleDetailsDataSource(UserDetailsRepository: UserDetailsRepository): UserDetailsDataSource {
        return UserDetailsRepository
    }
}