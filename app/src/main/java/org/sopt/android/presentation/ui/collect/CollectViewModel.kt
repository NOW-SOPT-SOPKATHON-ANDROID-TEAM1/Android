package org.sopt.android.presentation.ui.collect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.android.data.model.response.ResponseRememberAllDto
import org.sopt.android.data.repository.WeLikedItRepository
import org.sopt.android.util.view.UiState

class CollectViewModel(
    private val weLikedItRepository: WeLikedItRepository
) : ViewModel() {
    private val _getRememberAllState =
        MutableStateFlow<UiState<List<ResponseRememberAllDto>>>(UiState.Empty)
    val getRememberAllState get() = _getRememberAllState.asStateFlow()

    fun getRememberAll() {
        viewModelScope.launch {
            _getRememberAllState.value = UiState.Loading
            runCatching {
                weLikedItRepository.getRememberAll()
            }.onSuccess {
                _getRememberAllState.value = UiState.Success(it)
            }.onFailure { exception: Throwable ->
                _getRememberAllState.value = UiState.Error(exception.message)
            }
        }
    }
}