package org.sopt.sample.remote

import org.sopt.sample.data.dto.request.RequestSignupDto
import org.sopt.sample.data.dto.response.ResponseSignupDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {
    @POST("api/user/signup")
    fun signup(@Body request: RequestSignupDto): Call<ResponseSignupDto>
}