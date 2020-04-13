package com.example.comp_admin.a27retrofitlogin.helpers

import android.content.Context
import com.example.comp_admin.a27retrofitlogin.models.User

class SessionManager(var mContext: Context) {

    var sharedPreferences = mContext.getSharedPreferences(
        FILE_NAME,
        Context.MODE_PRIVATE
    ) //MODE_PRIVATE means only this application can access this class

    var editor = sharedPreferences.edit()

    companion object {
        private const val FILE_NAME = "session"
        private const val KEY_TOKEN = "token"
        private const val KEY_FIRST_NAME = "firstName"
        private const val KEY_EMAIL = "email"
        private const val KEY_MOBILE = "mobile"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    fun saveUser(user: User, token: String) {
        editor.putString(KEY_FIRST_NAME, user.firstName)
        editor.putString(KEY_EMAIL, user.email)
        editor.putString(KEY_MOBILE, user.mobile)
        editor.putString(KEY_TOKEN, token)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.commit() // to save changes
    }

    fun getUser(): User {
        var firstname = sharedPreferences.getString(KEY_FIRST_NAME, null)
        var email = sharedPreferences.getString(KEY_EMAIL, null)
        var mobile = sharedPreferences.getString(KEY_MOBILE, null)
        var user =
            User(firstName = firstname, email = email, mobile = mobile)//we do not save password
        return user
    }

    fun saveToken(token: String) {
        editor.putString(KEY_TOKEN, token)
        editor.commit()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }
}