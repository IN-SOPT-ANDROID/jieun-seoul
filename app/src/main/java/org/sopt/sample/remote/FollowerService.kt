package org.sopt.sample.remote

import org.sopt.sample.data.dto.response.ResponseFollowerDto
import retrofit2.Call
import retrofit2.http.GET

interface FollowerService {
    @GET("api/users?page=2")
    fun getFollowers(): Call<ResponseFollowerDto>
}