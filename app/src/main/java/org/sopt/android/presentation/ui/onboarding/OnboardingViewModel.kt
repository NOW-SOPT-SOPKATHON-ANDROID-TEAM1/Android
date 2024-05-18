package org.sopt.android.presentation.ui.onboarding

import android.net.Uri
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

class OnboardingViewModel(
) : ViewModel() {
    private val weLikedItRepository: WeLikedItRepository = WeLikedItRepository(ServicePool.weLikedItService)

    private val _postRememberState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postRememberState get() = _postRememberState.asStateFlow()

    private var _imageUri = Uri.EMPTY
    val imageUri get() = _imageUri

    fun setImageUri(uri: Uri) {
        _imageUri = uri
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