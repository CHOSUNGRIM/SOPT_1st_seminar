package com.example.sopt_1st_seminar

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptService {
    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    fun postLogin(
        @Body body : LoginRequestData
    ) : Call<LoginResponseData>

    @POST("/users/signup")
    fun postSignup(
        @Body body : SignupRequestData
    ) : Call<SignupResponseData>
}