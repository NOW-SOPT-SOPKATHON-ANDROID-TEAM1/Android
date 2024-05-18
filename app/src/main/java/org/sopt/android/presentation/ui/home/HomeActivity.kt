package org.sopt.android.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.android.data.model.response.ResponseRememberDto
import org.sopt.android.databinding.ActivityHomeBinding
import org.sopt.android.presentation.common.ViewModelFactory
import org.sopt.android.presentation.ui.collect.CollectActivity
import org.sopt.android.presentation.ui.record.RecordActivity
import org.sopt.android.presentation.ui.remind.DialogRemindFragment
import org.sopt.android.util.base.BindingActivity
import org.sopt.android.util.view.UiState

class HomeActivity : BindingActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }) {
    private val homeViewModel by viewModels<HomeViewModel> { ViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        initColletBtn()
        collectData()
    }

    private fun initLayout() {
        binding.btn1.setOnClickListener {
            homeViewModel.getRemember()
        }

        binding.btn2.setOnClickListener {
            navigateToRecord()
        }

        binding.tvHomeName.text = getUserName() + "님,"
    }

    private fun initColletBtn() {
        binding.tvBtn.setOnClickListener {
            startActivity(Intent(this@HomeActivity, CollectActivity::class.java))
        }
    }

    private fun collectData() {
        homeViewModel.getRememberState.flowWithLifecycle(lifecycle = lifecycle)
            .onEach { uiState ->
                when (uiState) {
                    is UiState.Success -> showDialogFragment(uiState.data)
                    else -> Unit
                }
            }.launchIn(lifecycleScope)
    }

    private fun showDialogFragment(responseRememberDto: ResponseRememberDto) {
        val dialogRemindFragment = DialogRemindFragment(
            text = responseRememberDto.caption,
            image = responseRememberDto.image,
            date = responseRememberDto.date
        )
        dialogRemindFragment.show(supportFragmentManager, "DialogFragment")
    }

    private fun getUserName(): String =
        applicationContext.getSharedPreferences("name", MODE_PRIVATE).getString("name", "")
            .orEmpty()

    private fun navigateToRecord() {
        startActivity(Intent(this@HomeActivity, RecordActivity::class.java))
    }
}
