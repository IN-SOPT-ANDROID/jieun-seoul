package org.sopt.sample.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignupDTO(
    @SerialName("newUser")
    val result: NewUser,
) {
    @Serializable
    data class NewUser(
        @SerialName("bio")
        val bio: String?,
        @SerialName("email")
        val email: String,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("password")
        val password: String,
        @SerialName("profileImage")
        val profileImage: String?
    )
}
