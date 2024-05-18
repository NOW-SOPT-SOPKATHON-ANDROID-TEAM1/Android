package org.sopt.android.presentation.ui.dummy

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import coil.load
import kotlinx.coroutines.flow.onEach
import org.sopt.android.data.ContentUriRequestBody
import org.sopt.android.databinding.ActivityDummyBinding
import org.sopt.android.presentation.common.ViewModelFactory
import org.sopt.android.util.base.BindingActivity
import org.sopt.android.util.context.showToast
import org.sopt.android.util.view.UiState

class DummyActivity : BindingActivity<ActivityDummyBinding>({ ActivityDummyBinding.inflate(it) }) {
    private val dummyViewModel by viewModels<DummyViewModel> { ViewModelFactory() }
    private var imageUri = Uri.EMPTY

    private val imageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            this.imageUri = imageUri ?: Uri.EMPTY
            binding.ivImage.load(imageUri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.ivImage.setOnClickListener {
            imageLauncher.launch("image/*")
        }

        binding.btSubmit.setOnClickListener {
            if (imageUri == Uri.EMPTY) {
                showToast("이미지를 선택해 주세요.")
            } else {
                dummyViewModel.submitArticle(
                    category = "좋았던일",
                    file = ContentUriRequestBody(
                        context = this,
                        uri = imageUri
                    ).toFormData(),
                    content = binding.etContent.text.toString()
                )
            }
        }
    }

    private fun collectData() {
        dummyViewModel.submitArticleState.flowWithLifecycle(lifecycle)
            .onEach { uiState ->
                when (uiState) {
                    is UiState.Success -> {
                        showToast("성공 !!!!")
                    }

                    is UiState.Error -> {
                        Log.e("ㅋㅋ", uiState.message.toString())
                    }

                    else -> Unit
                }
            }
    }
}