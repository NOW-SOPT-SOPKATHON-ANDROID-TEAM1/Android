package org.sopt.android.presentation.ui.onboarding

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import org.sopt.android.databinding.FragmentOnboardingBinding
import org.sopt.android.util.base.BindingFragment

class OnboardingFragment : Fragment() {
    private val viewModel by activityViewModels<OnboardingViewModel>()
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private var imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            viewModel.setImageUri(imageUri ?: Uri.EMPTY)
            binding.ivOnboardingImage.load(imageUri)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListeners()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addListeners() {
        binding.ivOnboardingImage.setOnClickListener {
            imageLauncher.launch("image/*")
        }

        binding.etOnboarding.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
            }
        )
    }
}