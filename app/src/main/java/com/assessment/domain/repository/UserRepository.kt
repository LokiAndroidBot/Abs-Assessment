package com.assessment.domain.repository

import com.assessment.domain.model.User

interface UserRepository {
    suspend fun getUsersList(inp: Int): List<User>
}
