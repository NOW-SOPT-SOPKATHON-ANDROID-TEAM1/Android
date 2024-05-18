package org.sopt.android.presentation.ui.record

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.sopt.android.R
import org.sopt.android.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    private lateinit var recordAdapter: RecordAdapter

    val mockRecordList = listOf(
        Record(
            imageUrl = R.drawable.ic_launcher_foreground,
            date = "이의경",
            title = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
        Record(
            imageUrl = R.drawable.ic_launcher_foreground,
            date = "이의경",
            title = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
        Record(
            imageUrl = R.drawable.ic_launcher_foreground,
            date = "이의경",
            title = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

    }


    private fun setupRecyclerView() {
        recordAdapter = RecordAdapter()
        binding.rvRecord.apply {
            adapter = recordAdapter
        }
        recordAdapter.setRecordList(mockRecordList)
    }

}