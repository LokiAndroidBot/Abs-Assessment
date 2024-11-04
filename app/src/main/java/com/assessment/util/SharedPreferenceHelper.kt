package com.assessment.util


import android.content.Context
import android.content.SharedPreferences
import com.assessment.domain.model.User
import com.google.gson.Gson

class SharedPrefHelper(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUser(user: User) {
        val editor = prefs.edit()
        val userJson = gson.toJson(user)
        editor.putString("saved_user", userJson)
        editor.apply()
    }

    fun getUser(): User? {
        val userJson = prefs.getString("saved_user", null) ?: return null
        return gson.fromJson(userJson, User::class.java)
    }
}
