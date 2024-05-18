package org.sopt.android.presentation.ui.collect

import androidx.activity.enableEdgeToEdge
import org.sopt.android.databinding.ActivityCollectBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.android.R
import org.sopt.android.databinding.ActivityHomeBinding
import org.sopt.android.util.base.BindingActivity


class CollectActivity :
    BindingActivity<ActivityCollectBinding>({ ActivityCollectBinding.inflate(it) }) {
    private lateinit var collectAdapter: CollectAdapter

    val mockRecordList = listOf(
        Collect(
            imageUrl = R.drawable.ic_launcher_foreground,
            title = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
        Collect(
            imageUrl = R.drawable.ic_launcher_foreground,
            title = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
        Collect(
            imageUrl = R.drawable.ic_launcher_foreground,
            title = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCollectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

    }


    private fun setupRecyclerView() {
        collectAdapter = CollectAdapter()
        binding.rvRecord.apply {
            adapter = collectAdapter
        }
        collectAdapter.setRecordList(mockRecordList)
    }

}