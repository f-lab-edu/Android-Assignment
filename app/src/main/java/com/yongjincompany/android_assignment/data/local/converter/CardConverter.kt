package com.yongjincompany.android_assignment.data.local.converter

import androidx.room.TypeConverter
import com.yongjincompany.android_assignment.data.local.entity.CardGradeType

class CardConverter {
    @TypeConverter
    fun fromCardGradeType(grade: CardGradeType): String {
        return grade.name
    }

    @TypeConverter
    fun toCardGradeType(grade: String): CardGradeType {
        return CardGradeType.valueOf(grade)
    }
}