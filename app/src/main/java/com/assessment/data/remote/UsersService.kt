package com.assessment.data.remote

import com.assessment.data.remote.responses.UsersListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {
    @GET("api")
    suspend fun getUsersList(
        @Query("results") count: Int // Use @Query to specify dynamic parameters
    ): UsersListResponse
}