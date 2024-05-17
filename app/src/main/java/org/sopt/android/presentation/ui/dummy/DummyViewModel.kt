package org.sopt.android.presentation.ui.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.android.data.repository.DummyRepository

class DummyViewModel(
    private val dummyRepository: DummyRepository
) : ViewModel() {

    fun getDummyUserList(page: Int = 2) {
        viewModelScope.launch {
            dummyRepository.getDummyUsers(page = page)
        }
    }
}