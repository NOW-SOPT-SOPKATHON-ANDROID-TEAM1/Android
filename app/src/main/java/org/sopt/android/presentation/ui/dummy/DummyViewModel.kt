package org.sopt.android.presentation.ui.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.sopt.android.data.repository.DummyRepository
import org.sopt.android.util.view.UiState

class DummyViewModel(
    private val dummyRepository: DummyRepository
) : ViewModel() {
    private val _submitArticleState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val submitArticleState get() = _submitArticleState.asStateFlow()

    fun submitArticle(category: String, file: MultipartBody.Part, content: String) {
        viewModelScope.launch {
            _submitArticleState.value = UiState.Loading
            runCatching {
                dummyRepository.postArticles(
                    category = category.toRequestBody("text/plain".toMediaType()),
                    file = file,
                    content = content.toRequestBody("text/plain".toMediaType())
                ).onSuccess {
                    _submitArticleState.value = UiState.Success(Unit)
                }.onFailure { throwable ->
                    _submitArticleState.value = UiState.Error(throwable.message)
                }
            }
        }
    }
}