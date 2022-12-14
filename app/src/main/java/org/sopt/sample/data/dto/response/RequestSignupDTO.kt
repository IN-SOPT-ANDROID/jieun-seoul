package org.sopt.sample.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDTO(

    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("password")
    val password: String,
)
