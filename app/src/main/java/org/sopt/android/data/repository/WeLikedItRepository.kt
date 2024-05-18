package org.sopt.android.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.sopt.android.data.model.response.ResponseRememberAllDto
import org.sopt.android.data.model.response.ResponseRememberDto
import org.sopt.android.data.service.WeLikedItService
import org.sopt.android.util.base.BaseResponse

class WeLikedItRepository(
    private val weLikedItService: WeLikedItService
) {
    suspend fun postRemember(image: MultipartBody.Part, caption: RequestBody) =
        weLikedItService.postRemember(image = image, caption = caption)

    suspend fun getRemember(): BaseResponse<ResponseRememberDto> = weLikedItService.getRemember()

    suspend fun getRememberAll(): List<ResponseRememberAllDto> = weLikedItService.getRememberAll()
}