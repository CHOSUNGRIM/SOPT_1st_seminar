package com.example.sopt_1st_seminar

import android.provider.ContactsContract

data class LoginResponseData (
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
    ) {
        data class Data(
            val email: String,
            val password: String,
            val userName: String
        )
}