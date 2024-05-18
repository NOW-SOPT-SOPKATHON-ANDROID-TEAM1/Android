package org.sopt.android.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import org.sopt.android.databinding.ActivityLoginBinding
import org.sopt.android.presentation.ui.home.HomeActivity
import org.sopt.android.util.base.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnStart.setOnClickListener {
            saveData()
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }
    }

    private fun saveData() {
        val pref = applicationContext.getSharedPreferences("name", MODE_PRIVATE)
        pref.edit().apply {
            putString("name", binding.etName.text.toString())
            putBoolean("isLogin", true)
            apply()
        }
    }
}