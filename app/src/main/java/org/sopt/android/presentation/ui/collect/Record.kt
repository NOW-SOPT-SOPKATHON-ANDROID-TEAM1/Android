package org.sopt.android.presentation.ui.collect

import androidx.annotation.DrawableRes

data class Collect(
    @DrawableRes val imageUrl: Int,
    val date: String,
    val title: String,
)