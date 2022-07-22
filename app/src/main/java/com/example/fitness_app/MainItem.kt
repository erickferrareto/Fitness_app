package com.example.fitness_app

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainItem(
    val id: Int,
    @DrawableRes val drawbleId: Int,
    @StringRes val textStringId: Int,
    val colorId: Int
)
