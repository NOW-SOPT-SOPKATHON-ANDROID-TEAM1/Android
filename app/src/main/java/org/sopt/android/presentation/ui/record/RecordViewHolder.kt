package org.sopt.android.presentation.ui.record

import androidx.recyclerview.widget.RecyclerView
import org.sopt.android.databinding.ItemRecordBinding

class RecordViewHolder(private val binding: ItemRecordBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(record: Record) {
        binding.run {
            ivImage.setImageResource(record.imageUrl)
            tvDate.text = record.date
            tvTitle.text = record.title
        }
    }
}