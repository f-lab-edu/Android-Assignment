package com.yongjincompany.android_assignment.data.repository

import com.yongjincompany.android_assignment.data.api.CardApi
import com.yongjincompany.android_assignment.data.model.response.Card

class CardRepository(private val cardApi: CardApi) {
    suspend fun fetchAllCardList(): Result<List<Card>> =
        kotlin.runCatching { cardApi.fetchAllCardList() }

    suspend fun fetchRecentReadCardList(): Result<List<Card>> =
        kotlin.runCatching { cardApi.fetchRecentReadCardList() }

    suspend fun changeCardReadStatus(
        id: Long,
        isRead: Boolean
    ): Result<Card> =
        kotlin.runCatching { cardApi.changeCardReadStatus(id, isRead) }
}