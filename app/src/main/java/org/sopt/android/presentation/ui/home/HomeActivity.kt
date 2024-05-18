package org.sopt.android.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.sopt.android.R
import org.sopt.android.databinding.ActivityHomeBinding
import org.sopt.android.databinding.ActivityLoginBinding
import org.sopt.android.presentation.ui.record.RecordActivity
import org.sopt.android.util.base.BindingActivity

class HomeActivity :  BindingActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getData()

        binding.tvBtn.setOnClickListener {
            startActivity(Intent(this@HomeActivity, RecordActivity::class.java))
        }
    }

    private fun getData() {
        val pref = applicationContext.getSharedPreferences("name", MODE_PRIVATE)
        val name = pref.getString("name", "")
        Log.d("HomeActivity", "name = $name")
        binding.tvHomeName.text = name
    }
}