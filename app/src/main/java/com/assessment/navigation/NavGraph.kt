package com.assessment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.assessment.presentation.userdetailscreen.UserDetailScreen
import com.assessment.presentation.userlist.UserListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.UserList.route) {
        composable(route = Screen.UserList.route) {
            UserListScreen(onItemClick = { userId ->
                navController.navigate("user_detail/$userId")
            })
        }

        composable(
            route = "user_detail/{userId}", arguments = listOf(navArgument("userId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            UserDetailScreen(userId)
        }
    }
}

