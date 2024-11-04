package com.assessment.domain.state

sealed class UserListEvent {
    data class SearchQueryChanged(val query: String) : UserListEvent()
}
