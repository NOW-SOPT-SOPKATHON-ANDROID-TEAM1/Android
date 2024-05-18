package org.sopt.android.presentation.ui.onboarding

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.android.data.ServicePool
import org.sopt.android.data.repository.WeLikedItRepository
import org.sopt.android.util.view.UiState

class OnboardingViewModel: ViewModel() {
    private val weLikedItRepository: WeLikedItRepository = WeLikedItRepository(ServicePool.weLikedItService)

    private val _postRememberState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postRememberState get() = _postRememberState.asStateFlow()

    private var _imageUriList = mutableListOf<Uri>(
        Uri.EMPTY,Uri.EMPTY,Uri.EMPTY,Uri.EMPTY,Uri.EMPTY,
    )
    fun getImageUriByIndex(index: Int): Uri = _imageUriList[index]
    fun setImageUriByIndex(index: Int, uri: Uri) {
        _imageUriList[index] = uri
        Log.d("Onboarding", _imageUriList.toString())
    }

    private var _contentList = mutableListOf<String>(
        "","","","",""
    )
    fun getContentByIndex(index: Int): String = _contentList[index]
    fun setContentByIndex(index: Int, content: String) {
        _contentList[index] = content
        Log.d("Onboarding", _contentList.toString())
    }




    fun postRemember(image: MultipartBody.Part, caption: String) {
        viewModelScope.launch {
            _postRememberState.value = UiState.Loading
            runCatching {
                weLikedItRepository.postRemember(
                    image = image,
                    caption = caption.toRequestBody("text/plain".toMediaType())
                )
            }.onSuccess {
                _postRememberState.value = UiState.Success(Unit)
            }.onFailure { throwable ->
                _postRememberState.value = UiState.Error(throwable.message)
            }
        }
    }
}