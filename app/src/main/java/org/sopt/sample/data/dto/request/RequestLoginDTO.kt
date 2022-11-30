package org.sopt.sample.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable // 응답 객체
data class RequestLoginDTO(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
)
