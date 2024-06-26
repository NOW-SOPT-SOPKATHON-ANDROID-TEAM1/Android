package org.sopt.android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseRememberDto(
    @SerialName("imageUrl")
    val image: String,
    @SerialName("caption")
    val caption: String,
    @SerialName("createdAt")
    val date: String
)