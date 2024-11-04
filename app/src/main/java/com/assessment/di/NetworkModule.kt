package com.assessment.di

import com.assessment.data.remote.UsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log request and response body
        }).connectTimeout(30, TimeUnit.SECONDS) // Set connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
            .writeTimeout(30, TimeUnit.SECONDS) // Set write timeout
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/") // Replace with your API base URL
            .client(okHttpClient) // Add the OkHttpClient to the Retrofit instance
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON serialization
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }
}
