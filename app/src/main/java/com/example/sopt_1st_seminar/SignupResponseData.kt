package com.example.sopt_1st_seminar

data class SignupResponseData(
    val data: SignupRequestData,
    val message: String,
    val status: Int,
    val success: Boolean
)