package dev.tiagomarcial.fitnesstracker.utils.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainItem(
    val id: Int,
    @DrawableRes val drawableID: Int,
    @StringRes val textStringID: Int,
    val color: Int
)
