package com.example.comp_admin.a27retrofitlogin.models

import java.io.Serializable

data class User(
    var firstName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var mobile: String? = null
) : Serializable {
    companion object {
        const val KEY_FIRST_NAME = "firstName"
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
        const val KEY_MOBILE = "mobile"

    }
}