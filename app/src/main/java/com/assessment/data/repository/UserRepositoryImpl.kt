package com.assessment.data.repository

import com.assessment.data.remote.UsersService
import com.assessment.data.remote.responses.Location
import com.assessment.domain.model.User
import com.assessment.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UsersService) : UserRepository {
    override suspend fun getUsersList(inp: Int): List<User> {
        return try {
            api.getUsersList(inp).results.mapNotNull { userResponse ->
                // Safely extract values or provide defaults if necessary
                User(
                    id = userResponse.id.toString(),
                    firstName = userResponse.name.first,
                    lastName = userResponse.name.last,
                    thumbnail = userResponse.picture.thumbnail,
                    image = userResponse.picture.large,
                    email = userResponse.email,
                    age = userResponse.dob.age.toString(),
                    address = formatAddress(userResponse.location)
                )
            }
        } catch (e: Exception) {
            // Log error or handle it as needed
            emptyList() // Returning an empty list if an error occurs
        }
    }

    private fun formatAddress(location: Location): String {
        return "${location.street.number}, ${location.street.name}\n" + "${location.city},\n" + "${location.state}, ${location.country} - ${location.postcode}"
    }
}
