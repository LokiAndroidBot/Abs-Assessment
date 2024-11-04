package com.assessment.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.domain.state.UserListEvent
import com.assessment.domain.state.UserListUiState
import com.assessment.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject
@OptIn(FlowPreview::class)
@HiltViewModel
open class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserListUiState())
    val state = _state.asStateFlow()

    private val searchQueryFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchQueryFlow.debounce(300).collect { query ->
                val count = query.toIntOrNull()
                fetchUsers(count)
            }
        }
    }

    fun onEvent(event: UserListEvent) {
        when (event) {
            is UserListEvent.SearchQueryChanged -> {
                viewModelScope.launch {
                    searchQueryFlow.value = event.query
                    _state.value = _state.value.copy(searchQuery = event.query)
                }
            }
        }
    }

    private fun fetchUsers(count: Int?) {
        if (count != null && count > 0) {
            viewModelScope.launch {
                _state.value = _state.value.copy(isLoading = true, error = null) // Clear error on new fetch
                try {
                    val users = getUsersUseCase(count)
                    _state.value = UserListUiState(users = users)
                } catch (e: Exception) {
                    _state.value = _state.value.copy(error = e.message ?: "An unknown error occurred")
                } finally {
                    _state.value = _state.value.copy(isLoading = false)
                }
            }
        }
    }
}

