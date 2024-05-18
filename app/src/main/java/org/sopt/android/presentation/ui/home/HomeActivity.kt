package org.sopt.android.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import org.sopt.android.databinding.ActivityHomeBinding
import org.sopt.android.presentation.ui.collect.CollectActivity
import org.sopt.android.presentation.ui.remind.DialogRemindFragment
import org.sopt.android.util.base.BindingActivity

class HomeActivity : BindingActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        initColletBtn()
    }
    private fun initLayout() {
        binding.btn1.setOnClickListener {
            showDialogFragment()
        }
        binding.tvHomeName.text = getUserName() + "님,"
    }
    private fun showDialogFragment(){
        val dialogRemindFragment = DialogRemindFragment(
            text = "Sample Text",
            image = "Sample Image"
        )
        dialogRemindFragment.show(supportFragmentManager, "DialogFragment")
    }

    private fun getUserName() : String = applicationContext.getSharedPreferences("name", MODE_PRIVATE).getString("name", "").orEmpty()

    private fun initColletBtn() {
        binding.tvBtn.setOnClickListener {
            startActivity(Intent(this@HomeActivity, CollectActivity::class.java))
        }
    }
}
