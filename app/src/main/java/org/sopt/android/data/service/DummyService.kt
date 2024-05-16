package org.sopt.android.data.service

import org.sopt.android.data.model.response.ResponseGetDummyUserListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyService {
    @GET("api/users")
    suspend fun getDummyListUserList(
        @Query("page") page: Int,
    ): ResponseGetDummyUserListDto
}
