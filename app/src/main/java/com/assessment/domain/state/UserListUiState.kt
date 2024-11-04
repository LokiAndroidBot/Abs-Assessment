package com.assessment.domain.state

import com.assessment.domain.model.User

data class UserListUiState(
    val users: List<User> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
