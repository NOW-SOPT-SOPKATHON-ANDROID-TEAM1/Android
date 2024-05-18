package org.sopt.android.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.android.data.model.response.ResponseRememberDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Part

interface WeLikedItService {
    @POST("/api/v1/remember")
    suspend fun postRemember(
        @Header("memberId") memberId: Int = 1,
        @Part image: MultipartBody.Part,
        @Part("caption") caption: RequestBody,
    )

    @GET("/api/v1/remember")
    suspend fun getRemember(): ResponseRememberDto
}