package org.sopt.android.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.android.data.model.response.ResponseRememberDto
import org.sopt.android.data.repository.WeLikedItRepository
import org.sopt.android.util.view.UiState

class HomeViewModel(
    private val weLikedItRepository: WeLikedItRepository
) : ViewModel() {
    private val _getRememberState = MutableStateFlow<UiState<ResponseRememberDto>>(UiState.Empty)
    val getRememberState get() = _getRememberState.asStateFlow()

    fun getRemember() {
        viewModelScope.launch {
            _getRememberState.value = UiState.Loading
            runCatching {
                weLikedItRepository.getRemember()
            }.onSuccess { responseRememberDto ->
                _getRememberState.value = UiState.Success(responseRememberDto)
            }.onFailure { throwable ->
                _getRememberState.value = UiState.Error(throwable.message)
            }
        }
    }
}