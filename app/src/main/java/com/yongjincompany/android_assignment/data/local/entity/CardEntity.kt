package com.yongjincompany.android_assignment.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey val id: Long,
    val description: String,
    val grade: CardGradeType,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "is_read") val isRead: Boolean,
    val name: String,
)

enum class CardGradeType {
    S,
    A,
    B,
    C,
    D,
}

