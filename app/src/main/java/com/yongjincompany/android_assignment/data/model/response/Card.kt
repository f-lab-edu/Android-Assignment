package com.yongjincompany.android_assignment.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val description: String,
    val grade: CardGradeType,
    val id: Long,
    val imageUrl: String,
    val isRead: Boolean,
    val name: String,
)

@Serializable
enum class CardGradeType {
    S,
    A,
    B,
    C,
    D,
}