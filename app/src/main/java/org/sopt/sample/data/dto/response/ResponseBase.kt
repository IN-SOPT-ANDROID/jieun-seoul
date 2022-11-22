package org.sopt.sample.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseBase<T>(
    val status: Int,
    val message: String,
    val data: T,
)