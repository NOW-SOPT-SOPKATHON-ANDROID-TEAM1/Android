package org.sopt.android.presentation.ui.collect

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.android.data.model.response.ResponseRememberAllDto
import org.sopt.android.databinding.ItemCollectBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CollectViewHolder(
    private val binding: ItemCollectBinding,
    private val showDialogFragment: (ResponseRememberAllDto) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(responseRememberAllDto: ResponseRememberAllDto) {
        binding.run {
            ivImage.load(responseRememberAllDto.imageUrl)
            tvTitle.text = responseRememberAllDto.caption
            tvDate.text = parseAndFormatDateTime(responseRememberAllDto.date)
            root.setOnClickListener {
                showDialogFragment(responseRememberAllDto)
            }
        }
    }

    fun parseAndFormatDateTime(dateTimeString: String): String {
        val dateTime = LocalDateTime.parse(dateTimeString)
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return dateTime.format(formatter)
    }
}