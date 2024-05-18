package org.sopt.android.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.android.util.base.BaseResponse
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DummyService {
    @Multipart
    @POST("/api/v1/test")
    suspend fun postArticles(
        @Part("category") category: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("content") content: RequestBody,
    ): Unit
}
