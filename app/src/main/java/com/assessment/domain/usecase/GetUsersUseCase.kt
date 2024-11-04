package com.assessment.domain.usecase

import com.assessment.domain.model.User
import com.assessment.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(searchQuery: Int): List<User> {
        return try {
            userRepository.getUsersList(searchQuery)
        } catch (e: Exception) {
            // Log error or handle it as needed
            emptyList() // Return an empty list or rethrow error based on needs
        }
    }
}
