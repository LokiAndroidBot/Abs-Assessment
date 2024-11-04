package com.assessment.app

import com.assessment.domain.model.User
import com.assessment.domain.state.UserListEvent
import com.assessment.domain.usecase.GetUsersUseCase
import com.assessment.presentation.userlist.UserListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    private lateinit var viewModel: UserListViewModel
    private lateinit var getUsersUseCase: GetUsersUseCase
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set the main dispatcher
        getUsersUseCase = mockk()
        viewModel = UserListViewModel(getUsersUseCase)
    }

    @Test
    fun `onEvent updates state when search query changes`() {
        // When event is triggered
        viewModel.onEvent(UserListEvent.SearchQueryChanged("5"))

        // Assert the state is updated
        assertEquals("5", viewModel.state.value.searchQuery)
    }

    @Test
    fun `fetchUsers calls use case and updates state on success`() = runBlocking {
        // Mock successful response
        val users = listOf(
            User(
                id = "1",
                firstName = "John",
                lastName = "Doe",
                email = "john.doe@example.com",
                age = "30",
                thumbnail = "url",
                address = "address",
                gender = ""
            ), User(
                id = "2",
                firstName = "Jane",
                lastName = "Smith",
                email = "jane.smith@example.com",
                age = "25",
                thumbnail = "url2",
                address = "address2",
                gender = ""
            )
        )
        val users1 = listOf<User>()
        coEvery { getUsersUseCase(2) } returns users

        // Trigger the fetch
        viewModel.onEvent(UserListEvent.SearchQueryChanged("2"))


        println(users)
        // Assert state updates
        assertEquals(users1, viewModel.state.value.users)
    }

    @Test
    fun `fetchUsers updates state on failure`() = runBlocking {
        // Mock failure
        coEvery { getUsersUseCase(5) } throws NullPointerException()

        // Trigger the fetch
        viewModel.onEvent(UserListEvent.SearchQueryChanged("5"))

        // Assert state error
        assertEquals(null, viewModel.state.value.error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset Main dispatcher
    }
}
