package org.sopt.android.presentation.ui.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.android.data.repository.WeLikedItRepository
import org.sopt.android.util.view.UiState

class RecordViewModel(
    private val weLikedItRepository: WeLikedItRepository
) : ViewModel() {
    private val _postRememberState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postRememberState get() = _postRememberState.asStateFlow()

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