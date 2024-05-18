package org.sopt.android.presentation.ui.onboarding

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import org.sopt.android.R
import org.sopt.android.databinding.FragmentOnboadingBinding
import org.sopt.android.util.base.BindingFragment


class OnboadingFragment(
    val content: String,
    val position: Int
) : BindingFragment<FragmentOnboadingBinding>({FragmentOnboadingBinding.inflate(it)}) {
    private val sharedViewModel by activityViewModels<OnboardingViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvTemporaryText.text = position.toString() +": "+content
        }
    }
}