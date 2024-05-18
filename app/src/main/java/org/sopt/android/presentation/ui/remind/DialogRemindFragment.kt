package org.sopt.android.presentation.ui.remind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import org.sopt.android.databinding.DialogRemindBinding
import org.sopt.android.util.base.BindingDialogFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DialogRemindFragment(
    private val text: String,
    private val image: String,
    private val date: String
) : BindingDialogFragment<DialogRemindBinding>({DialogRemindBinding.inflate(it)}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        with(binding) {
            remindImage.load(image)
            remindText.text = text
            tvRemindDate.text = parseAndFormatDateTime(date)
        }
    }

    fun parseAndFormatDateTime(dateTimeString: String): String {
        val dateTime = LocalDateTime.parse(dateTimeString)
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return dateTime.format(formatter)
    }
}