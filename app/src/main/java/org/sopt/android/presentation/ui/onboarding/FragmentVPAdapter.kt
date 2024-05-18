package org.sopt.android.presentation.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentVPAdapter (
    fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 5 // 페이지의 개수
    }

    override fun createFragment(position: Int): Fragment = OnboardingFragment()
}