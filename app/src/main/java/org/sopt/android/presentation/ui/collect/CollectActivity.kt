package org.sopt.android.presentation.ui.collect

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.android.data.model.response.ResponseRememberAllDto
import org.sopt.android.databinding.ActivityCollectBinding
import org.sopt.android.presentation.common.ViewModelFactory
import org.sopt.android.presentation.ui.remind.DialogRemindFragment
import org.sopt.android.util.base.BindingActivity
import org.sopt.android.util.view.UiState


class CollectActivity :
    BindingActivity<ActivityCollectBinding>({ ActivityCollectBinding.inflate(it) }) {
    private lateinit var collectAdapter: CollectAdapter
    private val collectViewModel by viewModels<CollectViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()
        collectViewModel.getRememberAll()
        collectData()
    }

    private fun setupRecyclerView() {
        collectAdapter = CollectAdapter(
            ::showDialogFragment
        )
        binding.rvRecord.apply {
            adapter = collectAdapter
        }
    }

    private fun collectData() {
        collectViewModel.getRememberAllState.flowWithLifecycle(lifecycle)
            .onEach { uiState ->
                when (uiState) {
                    is UiState.Success -> collectAdapter.setRecordList(uiState.data)
                    else -> Unit
                }
            }.launchIn(lifecycleScope)
    }

    private fun showDialogFragment(responseRememberAllDto: ResponseRememberAllDto) {
        val dialogRemindFragment = DialogRemindFragment(
            text = responseRememberAllDto.caption,
            image = responseRememberAllDto.imageUrl,
            date = responseRememberAllDto.date
        )
        dialogRemindFragment.show(supportFragmentManager, "DialogFragment")
    }

}