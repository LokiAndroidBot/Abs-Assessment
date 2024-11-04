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
git clone https://github.com/LokiAndroidBot/Abs-Assessment </br>
cd Abs-Assessment</br>
Open the project in Android Studio.</br>

### Build the project and install the app on a physical device or emulator.

#### Architecture
The application follows the MVI Architecture along with MVVM (Model-View-ViewModel) architectural pattern, which helps to separate the user interface from the business logic.

#### Components
User Repository
The UserRepositoryImpl class is responsible for fetching user data from the remote API. It maps the API response to the User data class.

#### Use Cases
The GetUsersUseCase class is invoked to fetch user data based on the input provided by the user.

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) { </br>
    suspend operator fun invoke(searchQuery: Int): List<User> { </br>
        return userRepository.getUsersList(searchQuery) </br>
    } </br>
} </br>

#### ViewModel
The UserListViewModel manages the UI state and handles user input events.

#### UI
The UI is built using Jetpack Compose, providing a modern, reactive interface. Key components include UserListScreen and UserDetailScreen.

#### Navigation
The app uses Jetpack Navigation for seamless transitions between screens.

@Composable
fun NavGraph(navController: NavHostController) { </br>
    NavHost(navController = navController, startDestination = Screen.UserList.route) { </br>
        // Implementation here... </br>
    } </br>
}</br>

#### Run the Unit Test
bash</br>
./gradlew test</br>


