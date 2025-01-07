package com.yongjincompany.android_assignment.data.mapper

import com.yongjincompany.android_assignment.data.local.entity.CardEntity
import com.yongjincompany.android_assignment.data.local.entity.CardGradeType
import com.yongjincompany.android_assignment.data.remote.model.response.Card

fun Card.toCardEntity(): CardEntity {
    return CardEntity(
        id,
        description,
        grade.toLocalCardGradeType(),
        imageUrl,
        isRead,
        name
    )
}

fun CardEntity.toCardResponse(): Card {
    return Card(
        id,
        description,
        grade.toRemoteCardGradeType(),
        imageUrl,
        isRead,
        name
    )
}

fun Card.CardGradeType.toLocalCardGradeType(): CardGradeType {
    return when (this) {
        Card.CardGradeType.S -> CardGradeType.S
        Card.CardGradeType.A -> CardGradeType.A
        Card.CardGradeType.B -> CardGradeType.B
        Card.CardGradeType.C -> CardGradeType.C
        Card.CardGradeType.D -> CardGradeType.D
    }
}

fun CardGradeType.toRemoteCardGradeType(): Card.CardGradeType {
    return when (this) {
        CardGradeType.S -> Card.CardGradeType.S
        CardGradeType.A -> Card.CardGradeType.A
        CardGradeType.B -> Card.CardGradeType.B
        CardGradeType.C -> Card.CardGradeType.C
        CardGradeType.D -> Card.CardGradeType.D
    }
}