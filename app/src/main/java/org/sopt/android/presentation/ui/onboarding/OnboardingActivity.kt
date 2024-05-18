package org.sopt.android.presentation.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.android.R
import org.sopt.android.databinding.ActivityOnboardingBinding
import org.sopt.android.databinding.CustomTabIndicatorBinding
import org.sopt.android.util.base.BindingActivity

class OnboardingActivity : BindingActivity<ActivityOnboardingBinding>({ ActivityOnboardingBinding.inflate(it) }) {
    private val viewModel by viewModels<OnboardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVPAdapter()
        initTapLayout()
        initView()
    }

    private fun initVPAdapter() {
        with(binding) {
            val fragment1 = OnboardingFragment()
            val fragment2 = OnboardingFragment()
            val fragment3 = OnboardingFragment()
            val fragment4 = OnboardingFragment()
            val fragment5 = OnboardingFragment()

            fragment1.setIndex(0)
            fragment2.setIndex(1)
            fragment3.setIndex(2)
            fragment4.setIndex(3)
            fragment5.setIndex(4)

            vpOnboarding.adapter =  FragmentVPAdapter(
                fragmentList = listOf(
                    fragment1,
                    fragment2,
                    fragment3,
                    fragment4,
                    fragment5,
                ),
                fragmentActivity = this@OnboardingActivity)

            vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when(position) {
                        0 -> binding.btnNext.text = "다른 순간.. 기록할래?"
                        1 -> binding.btnNext.text = "다른 순간 또.. 기록할래?"
                        2 -> binding.btnNext.text = "하나 더.. 기록할래?"
                        3 -> binding.btnNext.text = "마지막으로.. 기록할래?"
                        4 -> binding.btnNext.text = "이제.. 시작해볼까?"
                        else -> ""
                    }
                }
            })
        }
    }

    private fun initTapLayout() {
        with(binding) {
            TabLayoutMediator(layoutTabIndicator, vpOnboarding) { tab, position ->
                tab.customView = layoutInflater.inflate(R.layout.custom_tab_indicator, null)
            }.attach()

            layoutTabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val tabBinding = tab?.customView?.let { CustomTabIndicatorBinding.bind(it) }
                    tabBinding?.indicator?.isSelected = true
                    //tab?.customView?.findViewById<View>(R.id.indicator)?.isSelected = true
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val tabBinding = tab?.customView?.let { CustomTabIndicatorBinding.bind(it) }
                    tabBinding?.indicator?.isSelected = false
                    //tab?.customView?.findViewById<View>(R.id.indicator)?.isSelected = false
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // Do nothing
                }
            })
        }
    }

    private fun initView() {
        with(binding) {
            btnNext.setOnClickListener {
                val currentItem = vpOnboarding.currentItem
                val itemCount = vpOnboarding.adapter?.itemCount!!
                if (currentItem < itemCount - 1) {
                    vpOnboarding.currentItem = currentItem + 1
                }
            }

            btnBack.setOnClickListener{
                val currentItem = vpOnboarding.currentItem
                if (currentItem > 0) {
                    vpOnboarding.currentItem = currentItem - 1
                }
            }


        }
    }

}