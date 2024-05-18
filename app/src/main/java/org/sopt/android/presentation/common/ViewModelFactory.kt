package org.sopt.android.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.android.data.ServicePool
import org.sopt.android.data.repository.WeLikedItRepository
import org.sopt.android.presentation.ui.record.RecordViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordViewModel::class.java)) {
            return RecordViewModel(WeLikedItRepository(ServicePool.weLikedItService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}