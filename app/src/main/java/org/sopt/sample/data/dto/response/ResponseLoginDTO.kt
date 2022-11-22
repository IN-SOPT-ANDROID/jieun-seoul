package org.sopt.sample.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String?,
    @SerialName("bio")
    val bio: String?,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)
