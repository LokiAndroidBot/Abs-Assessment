package com.assessment.navigation

object Screen {
    object UserList {
        const val route = "user_list"
    }


    object UserDetail {
        const val route = "user_detail"

        fun passUser(user: String): String {

            return "user_detail/${user}" // You need a function to convert User to JSON
        }
    }
}
