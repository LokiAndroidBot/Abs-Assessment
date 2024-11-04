package com.assessment.presentation.userlist

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assessment.domain.model.User
import com.assessment.domain.state.UserListEvent
import com.assessment.presentation.userdetailscreen.UserDetailScreen
import com.google.gson.Gson

@Composable
fun UserListScreen(onItemClick: (String) -> Unit, viewModel: UserListViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column {
        // Input Field for User Count
        TextField(
            value = state.searchQuery,
            onValueChange = { newValue ->
                // Validate that the new input is digits only
                if (newValue.all { it.isDigit() }) {
                    viewModel.onEvent(UserListEvent.SearchQueryChanged(newValue))
                }
            },
            label = { Text("Enter number of users to fetch (digits only)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Loading State
        if (state.isLoading) {
            LoadingIndicator()
        }

        // Error Message Display
        state.error?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(16.dp))
        }

        // No Users Found Message
        if (!state.isLoading && state.users.isEmpty() && state.searchQuery.isNotEmpty()) {
            NoUsersFoundMessage()
        }

        // Display the User List
        LazyColumn {
            items(state.users) { user ->
                UserListItem(user = user) {
                    onItemClick(Uri.encode(Gson().toJson(user)))
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Fetching Users",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp) // Customize size if needed
        )
    }
}

@Composable
fun NoUsersFoundMessage() {
    Text(
        text = "No users found for the given number.",
        color = Color.Gray,
        modifier = Modifier.padding(16.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    val mockUser = User(
        id = "1",
        firstName = "John",
        lastName = "Doe",
        age = "30",
        email = "john.doe@example.com",
        address = "123 Main St, Springfield, USA",
        thumbnail = "https://example.com/profile.jpg"
    )

    val userJson = Gson().toJson(mockUser)

    UserDetailScreen(user = Uri.encode(userJson))
}
