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
            val fragment1 = OnboardingFragment(0)
            val fragment2 = OnboardingFragment(1)
            val fragment3 = OnboardingFragment(2)
            val fragment4 = OnboardingFragment(3)
            val fragment5 = OnboardingFragment(4)

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
                    with(binding) {
                        when (position) {
                            0 -> {
                                btnNext.text = "다른 순간.. 기록할래?"
                                //tvDescription.text= "일상 속에서 느꼈던\n작은 행복의 순간을 기록해주세요"
                            }

                            1 -> {
                                btnNext.text = "다른 순간 또.. 기록할래?"
                                //tvDescription.text= "취미 활동을 하며\n가장 즐거웠던 순간을 기록해주세요"
                            }

                            2 -> {
                                btnNext.text = "하나 더.. 기록할래?"
                                //tvDescription.text= "당신이 자랑스러웠던\n성취의 순간을 기록해주세요"
                            }

                            3 -> {
                                btnNext.text = "마지막으로.. 기록할래?"
                                //tvDescription.text= "친구들과의 즐거웠던 추억을 기록해주세요"
                            }

                            4 -> {
                                btnNext.text = "이제.. 시작해볼까?"
                                //tvDescription.text= "최근에 가장 재밌게 놀았던\n추억을 기록해주세요"
                            }

                            else -> ""
                        }
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