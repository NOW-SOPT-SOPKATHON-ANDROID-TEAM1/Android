package org.sopt.android.presentation.ui.record

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.android.data.ContentUriRequestBody
import org.sopt.android.databinding.ActivityRecordBinding
import org.sopt.android.presentation.common.ViewModelFactory
import org.sopt.android.presentation.ui.home.HomeActivity
import org.sopt.android.util.base.BindingActivity
import org.sopt.android.util.context.showToast
import org.sopt.android.util.view.UiState

class RecordActivity :
    BindingActivity<ActivityRecordBinding>({ ActivityRecordBinding.inflate(it) }) {
    private val recordViewModel by viewModels<RecordViewModel> { ViewModelFactory() }

    private var imageUri = Uri.EMPTY

    private val imageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            this.imageUri = imageUri ?: Uri.EMPTY
            if(this.imageUri != Uri.EMPTY) {
                binding.tvTitle.text = ""
            } else {
                binding.tvTitle.text = "사진을 불러오세요"
            }
            binding.ivRecordImage.load(imageUri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.ivRecordArrowLeft.setOnClickListener {
            finish()
        }

        binding.ivRecordImage.setOnClickListener {
            imageLauncher.launch("image/*")
        }

        binding.btnRecordSave.setOnClickListener {
            if (imageUri == Uri.EMPTY) {
                showToast("이미지를 선택해 주세요.")
                return@setOnClickListener
            } else {
                recordViewModel.postRemember(
                    image = ContentUriRequestBody(
                        context = this,
                        uri = imageUri
                    ).toFormData(),
                    caption = binding.etRecord.text.toString()
                )
            }
        }
    }

    private fun collectData() {
        recordViewModel.postRememberState.flowWithLifecycle(lifecycle = lifecycle)
            .onEach { uiState ->
                when (uiState) {
                    is UiState.Success -> navigateToHome()
                    else -> Unit
                }
            }.launchIn(lifecycleScope)
    }

    private fun navigateToHome() {
        Intent(this@RecordActivity, HomeActivity::class.java).run {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }
}