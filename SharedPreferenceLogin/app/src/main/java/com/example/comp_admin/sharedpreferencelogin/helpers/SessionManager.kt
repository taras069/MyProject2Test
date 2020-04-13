package com.example.comp_admin.sharedpreferencelogin.helpers

import android.content.Context
import android.content.SharedPreferences

class SessionManager(var mContext: Context) {
    var mySharedPreferences: SharedPreferences =
        mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = mySharedPreferences.edit()

    companion object {
        private const val FILE_NAME = "my_file"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_NAME = "name"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    fun register(name: String, email: String, password: String) {
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_PASSWORD, password)
        editor.commit()
    }

    fun login(email: String, password: String): Boolean {
        var savedEmail = mySharedPreferences.getString(KEY_EMAIL, null)
        var savedPassword = mySharedPreferences.getString(KEY_PASSWORD, null)
        return savedEmail.equals(email) && savedPassword.equals(password)
    }
    fun logout(){
        editor.clear()
        editor.commit()
    }


}