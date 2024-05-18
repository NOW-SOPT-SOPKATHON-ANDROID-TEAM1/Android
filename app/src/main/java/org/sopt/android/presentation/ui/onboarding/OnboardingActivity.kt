package org.sopt.android.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.android.R
import org.sopt.android.databinding.ActivityOnboardingBinding
import org.sopt.android.util.base.BindingActivity

class OnboardingActivity : BindingActivity<ActivityOnboardingBinding>({ ActivityOnboardingBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setVPAdapter()
        setTapLayout()
    }

    private fun setVPAdapter() {
        with(binding) {
            vpOnboarding.adapter = OnboardingVPAdapter(strings = arrayListOf(
                "fragment1","fragment2","fragment3","fragment4","fragment5"))
        }
    }

    private fun setTapLayout() {
        with(binding) {
            TabLayoutMediator(layoutTabIndicator, vpOnboarding) { tab, position ->
                tab.customView = layoutInflater.inflate(R.layout.custom_tab, null)
            }.attach()

            layoutTabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.customView?.findViewById<View>(R.id.indicator)?.isSelected = true
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView?.findViewById<View>(R.id.indicator)?.isSelected = false
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // Do nothing
                }
            })
        }


    }

}