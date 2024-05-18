package org.sopt.android.presentation.ui.collect

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.android.data.model.response.ResponseRememberAllDto
import org.sopt.android.databinding.ItemCollectBinding

class CollectViewHolder(
    private val binding: ItemCollectBinding,
    private val showDialogFragment: (ResponseRememberAllDto) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(responseRememberAllDto: ResponseRememberAllDto) {
        binding.run {
            ivImage.load(responseRememberAllDto.imageUrl)
            tvTitle.text = responseRememberAllDto.caption
            tvDate.text = responseRememberAllDto.date
            root.setOnClickListener {
                showDialogFragment
            }
        }
    }
}