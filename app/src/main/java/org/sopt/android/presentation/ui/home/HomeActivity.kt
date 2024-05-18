package org.sopt.android.presentation.ui.home

import android.os.Bundle
import org.sopt.android.databinding.ActivityHomeBinding
import org.sopt.android.presentation.ui.remind.DialogRemindFragment
import org.sopt.android.util.base.BindingActivity

class HomeActivity : BindingActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }
    private fun initLayout() {
        binding.btn1.setOnClickListener {
            showDialogFragment()
        }
    }
    private fun showDialogFragment(){
        val dialogRemindFragment = DialogRemindFragment(
            text = "Sample Text",
            image = "Sample Image"
        )
        dialogRemindFragment.show(supportFragmentManager, "DialogFragment")
    }
}
