package org.sopt.android.presentation.ui.onboarding

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import coil.load
import org.sopt.android.databinding.FragmentOnboardingBinding
import org.sopt.android.util.base.BindingFragment

class OnboardingFragment :
    BindingFragment<FragmentOnboardingBinding>({ FragmentOnboardingBinding.inflate(it) }) {
    private val viewModel by activityViewModels<OnboardingViewModel>()

    private val imageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            viewModel.setImageUri(imageUri ?: Uri.EMPTY)
            binding.ivOnboardingImage.load(imageUri)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListeners()
    }

    private fun addListeners() {
        binding.ivOnboardingImage.setOnClickListener {
            imageLauncher.launch("image/*")
        }
    }
}