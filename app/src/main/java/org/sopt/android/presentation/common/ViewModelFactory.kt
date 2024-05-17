package org.sopt.android.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.android.data.ServicePool
import org.sopt.android.data.repository.DummyRepository
import org.sopt.android.presentation.ui.dummy.DummyViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DummyViewModel::class.java)) {
            return DummyViewModel(DummyRepository(ServicePool.dummyService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}