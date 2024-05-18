package org.sopt.android.presentation.ui.remind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import org.sopt.android.databinding.DialogRemindBinding
import org.sopt.android.util.base.BindingDialogFragment

class DialogRemindFragment(
    private val text: String,
    private val image: String
) : BindingDialogFragment<DialogRemindBinding>({DialogRemindBinding.inflate(it)}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        with(binding) {
            remindImage.load(image)
            remindText.text = text
        }
    }
}
