package org.sopt.android.data.model.requset

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDummyDto(
    @SerialName("id")
    val id: String,
)