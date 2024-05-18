package org.sopt.android.presentation.ui.collect

import androidx.recyclerview.widget.RecyclerView
import org.sopt.android.databinding.ItemCollectBinding

class CollectViewHolder(private val binding: ItemCollectBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(collect: Collect) {
        binding.run {
            ivImage.setImageResource(collect.imageUrl)
            tvTitle.text = collect.title
        }
    }
}