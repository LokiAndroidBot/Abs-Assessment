# Abs-Assessment
## Overview 
The User Management App allows users to fetch and display a list of users from a remote API, view their details, and manage a user interface using Kotlin and Jetpack Compose. It utilizes MVI architecture along with MVVM and includes dependency injection with Hilt.

## Features
Fetch a list of random users based on a user-defined number. </br>
View detailed information about each user, including name, age, email, and address.</br>
Responsive and user-friendly UI built with Jetpack Compose. </br>
Implements clean architecture with separation of concerns. </br>


## Requirements
Android Studio 4.1 or higher</br>
Kotlin 1.5 or higher </br>
Gradle 7.0 or higher </br>
Getting Started </br>


## Clone the repository:
open version control </br>
git clone https://github.com/yourusername/user-management-app.git</br>
cd Abs-Assessment</br>
Open the project in Android Studio.</br>

## Build the project and install the app on a physical device or emulator.

Architecture
The application follows the MVVM (Model-View-ViewModel) architectural pattern, which helps to separate the user interface from the business logic.

Components
User Repository
The UserRepositoryImpl class is responsible for fetching user data from the remote API. It maps the API response to the User data class.

## UserRepositoryImpl
class UserRepositoryImpl @Inject constructor(private val api: UsersService) : UserRepository {
    override suspend fun getUsersList(inp: Int): List<User> {
        return api.getUsersList(inp).results.map {
            User(
                id = it.id.toString(),
                firstName = it.name.first,
                lastName = it.name.last,
                thumbnail = it.picture.thumbnail,
                image = it.picture.large,
                email = it.email,
                age = it.dob.age.toString(),
                address = "${it.location.street.number}, ${it.location.street.name} \n ${it.location.city}, \n ${it.location.state}, ${it.location.country} - ${it.location.postcode}"
            )
        }
    }
}

Use Cases
The GetUsersUseCase class is invoked to fetch user data based on the input provided by the user.

kotlin
class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(searchQuery: Int): List<User> {
        return userRepository.getUsersList(searchQuery)
    }
}
ViewModel
The UserListViewModel manages the UI state and handles user input events.

kotlin
@HiltViewModel
class UserListViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {
    private val _state = MutableStateFlow(UserListUiState())
    val state = _state.asStateFlow()

    // Handle user input and fetch users accordingly
    fun onEvent(event: UserListEvent) {
        // Implementation here...
    }
}
UI
The UI is built using Jetpack Compose, providing a modern, reactive interface. Key components include UserListScreen and UserDetailScreen.

kotlin
@Composable
fun UserListScreen(onItemClick: (String) -> Unit) {
    // Implementation here...
}
Navigation
The app uses Jetpack Navigation for seamless transitions between screens.

kotlin
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.UserList.route) {
        // Implementation here...
    }
}


bash
./gradlew testDebugUnitTest
To verify code coverage:

bash
./gradlew jacocoTestCoverageVerification


