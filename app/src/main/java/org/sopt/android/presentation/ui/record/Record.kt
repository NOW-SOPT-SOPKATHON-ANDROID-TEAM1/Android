package org.sopt.android.presentation.ui.record

import androidx.annotation.DrawableRes

data class Record(
    @DrawableRes val imageUrl: Int,
    val date: String,
    val title: String,
)