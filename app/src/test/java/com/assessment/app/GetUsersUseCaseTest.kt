package com.assessment.app

import com.assessment.domain.model.User
import com.assessment.domain.repository.UserRepository
import com.assessment.domain.usecase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUsersUseCaseTest {

    private val userRepository = mockk<UserRepository>(relaxed = true) // Create a mock repository
    private val getUsersUseCase = GetUsersUseCase(userRepository) // Instantiate the use case

    @Test
    fun `invoke should return user list from repository`() = runBlocking {
        // Given
        val searchQuery = 2
        val expectedUsers = listOf(
            User(
                id = "1",
                firstName = "John",
                lastName = "Doe",
                email = "john.doe@example.com",
                address = "123 Main St",
                thumbnail = "https://example.com/image1.jpg"
            ), User(
                id = "2",
                firstName = "Jane",
                lastName = "Smith",
                email = "jane.smith@example.com",
                address = "456 Elm St",
                thumbnail = "https://example.com/image2.jpg"
            )
        )

        // When
        coEvery { userRepository.getUsersList(searchQuery) } returns expectedUsers // Mock the repository behavior
        val result = getUsersUseCase(searchQuery) // Invoke the use case

        // Then
        assertEquals(expectedUsers, result) // Assert that the result matches expected
        coVerify { userRepository.getUsersList(searchQuery) } // Verify that the repository method was called
    }
}
