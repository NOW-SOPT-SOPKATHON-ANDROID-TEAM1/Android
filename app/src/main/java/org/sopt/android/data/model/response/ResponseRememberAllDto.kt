package org.sopt.android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseRememberAllDto(
    @SerialName("caption")
    val caption: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("createdAt")
    val date: String
)

