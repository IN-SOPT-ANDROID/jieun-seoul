package org.sopt.sample.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {
    @POST("api/user/signup")
    fun signup(@Body request: RequestSignupDTO): Call<ResponseSignupDTO>
}