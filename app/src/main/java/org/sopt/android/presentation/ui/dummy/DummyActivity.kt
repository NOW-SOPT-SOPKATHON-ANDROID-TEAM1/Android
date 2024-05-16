package org.sopt.android.presentation.ui.dummy

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.android.databinding.ActivityDummyBinding
import org.sopt.android.presentation.common.ViewModelFactory
import org.sopt.android.util.base.BindingActivity

class DummyActivity : BindingActivity<ActivityDummyBinding>({ ActivityDummyBinding.inflate(it) }) {
    private val dummyViewModel by viewModels<DummyViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dummyViewModel.getDummyUserList(page = 2)
    }
}