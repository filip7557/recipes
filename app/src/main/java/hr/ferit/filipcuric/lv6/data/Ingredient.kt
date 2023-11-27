package hr.ferit.filipcuric.lv6.data

import androidx.annotation.DrawableRes

data class Ingredient(
    @DrawableRes val image: Int,
    val title: String,
    val subtitle: String,
)
