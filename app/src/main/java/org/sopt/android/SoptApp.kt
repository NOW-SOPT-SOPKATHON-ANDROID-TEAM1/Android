package org.sopt.android

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class SoptApp : Application() {
    override fun onCreate() {
        super.onCreate()

        disableDarkMode()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}