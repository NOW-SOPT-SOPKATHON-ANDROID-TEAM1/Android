package org.sopt.android.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.android.data.model.response.ResponseGetDummyUserListDto
import org.sopt.android.data.service.DummyService
import org.sopt.android.util.view.UiState

class DummyRepository(
    private val dummyService: DummyService
) {
    private val _dummyUsers = MutableStateFlow<UiState<ResponseGetDummyUserListDto>>(UiState.Empty)
    val dummyUsers get() = _dummyUsers.asStateFlow()

    suspend fun getDummyUsers(page: Int) =
        runCatching {
            dummyService.getDummyListUsers(page = page)
        }
}