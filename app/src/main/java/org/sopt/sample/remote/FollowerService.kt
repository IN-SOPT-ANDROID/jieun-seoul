package org.sopt.sample.remote

import retrofit2.Call
import retrofit2.http.GET

interface FollowerService {
    @GET("api/users?page=2")
    fun getData(): Call<ResponseFollowerDTO>
}