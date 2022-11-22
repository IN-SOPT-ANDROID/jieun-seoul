package org.sopt.sample.remote

import org.sopt.sample.data.dto.response.RequestLoginDTO
import org.sopt.sample.data.dto.response.ResponseBase
import org.sopt.sample.data.dto.response.ResponseLoginDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/user/signin")
    fun login(
        @Body request: RequestLoginDTO
    ): Call <ResponseBase<ResponseLoginDTO>>
}