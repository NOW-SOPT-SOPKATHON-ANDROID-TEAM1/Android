package org.sopt.android.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.android.data.service.DummyService

class DummyRepository(
    private val dummyService: DummyService
) {
    suspend fun postArticles(
        category: RequestBody,
        file: MultipartBody.Part,
        content: RequestBody
    ) =
        runCatching {
            dummyService.postArticles(category = category, file = file, content = content)
        }
}