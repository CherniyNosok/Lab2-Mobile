package ru.kalinin.lab2_mobile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Artwork(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val author: Int,
    @StringRes val year: Int
)