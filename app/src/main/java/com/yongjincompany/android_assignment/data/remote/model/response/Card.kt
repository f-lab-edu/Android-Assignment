package com.yongjincompany.android_assignment.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: Long,
    val description: String,
    val grade: CardGradeType,
    val imageUrl: String,
    val isRead: Boolean,
    val name: String,
) {
    @Serializable
    enum class CardGradeType {
        S,
        A,
        B,
        C,
        D,
    }
}

