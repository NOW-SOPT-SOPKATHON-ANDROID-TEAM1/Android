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

class OnboardingFragment(private val idx: Int) : Fragment() {
    private val viewModel by activityViewModels<OnboardingViewModel>()
    private var _binding: FragmentOnboardingBinding? = null

    var topText: String = when(idx) {
        0 -> "일상 속에서 느꼈던\n작은 행복의 순간을 기록해주세요"

        1 -> "취미 활동을 하며\n가장 즐거웠던 순간을 기록해주세요"


        2 -> "당신이 자랑스러웠던\n성취의 순간을 기록해주세요"


        3 -> "친구들과의 즐거웠던 추억을 기록해주세요"


        4 -> "최근에 가장 재밌게 놀았던\n추억을 기록해주세요"
        else -> ""
    }

    private val binding get() = _binding!!

    private var imageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            //viewModel.setImageUri(imageUri ?: Uri.EMPTY)
            viewModel.setImageUriByIndex(idx, imageUri?:Uri.EMPTY)
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

        binding.tvOnboardingQuestion.text = topText

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
                viewModel.setContentByIndex(index = idx, content = text.toString())
            }
        )
    }
}