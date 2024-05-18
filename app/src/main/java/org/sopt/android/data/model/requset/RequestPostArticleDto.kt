package org.sopt.android.data.model.requset

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostArticleDto(
    @SerialName("category")
    val category: String,
    @SerialName("file")
    val file: String,
    @SerialName("content")
    val content: String
)