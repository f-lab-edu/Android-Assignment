package com.yongjincompany.android_assignment.data

import androidx.annotation.DrawableRes

data class Card(
    val id: Int,
    val grade: Grade,
    val name: String,
    @DrawableRes val img: Int,
    val description: String? = null
)

enum class Grade {
    S,
    A,
    B,
    C,
    D
}
