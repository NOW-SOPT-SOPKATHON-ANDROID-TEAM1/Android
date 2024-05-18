package org.sopt.android.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.android.data.ServicePool
import org.sopt.android.data.repository.WeLikedItRepository
import org.sopt.android.presentation.ui.collect.CollectViewModel
import org.sopt.android.presentation.ui.home.HomeViewModel
import org.sopt.android.presentation.ui.record.RecordViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordViewModel::class.java)) {
            return RecordViewModel(WeLikedItRepository(ServicePool.weLikedItService)) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(WeLikedItRepository(ServicePool.weLikedItService)) as T
        } else if (modelClass.isAssignableFrom(CollectViewModel::class.java)) {
            return CollectViewModel(WeLikedItRepository(ServicePool.weLikedItService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}