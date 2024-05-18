package org.sopt.android.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.android.databinding.ActivitySplashBinding
import org.sopt.android.presentation.ui.home.HomeActivity
import org.sopt.android.presentation.ui.login.LoginActivity
import org.sopt.android.util.base.BindingActivity

class SplashActivity :
    BindingActivity<ActivitySplashBinding>({ ActivitySplashBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        lifecycleScope.launch {
            delay(1000L)
            setAutoLogin()
            finish()
        }
    }

    private fun setAutoLogin() {
        if (applicationContext.getSharedPreferences("name", MODE_PRIVATE)
                .getBoolean("isLogin", false)
        ) {
            navigateToHome()
        } else navigateToLogin()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
    }

    private fun navigateToHome() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
    }

}