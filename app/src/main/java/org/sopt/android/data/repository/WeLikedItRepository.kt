package org.sopt.android.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.android.data.model.response.ResponseRememberDto
import org.sopt.android.data.service.WeLikedItService

class WeLikedItRepository(
    private val weLikedItService: WeLikedItService
) {
    suspend fun postRemember(image: MultipartBody.Part, caption: RequestBody) =
        weLikedItService.postRemember(image = image, caption = caption)

    suspend fun getRemember(): ResponseRememberDto = weLikedItService.getRemember()
}