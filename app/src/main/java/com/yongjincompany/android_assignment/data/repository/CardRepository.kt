package com.yongjincompany.android_assignment.data.repository

import com.yongjincompany.android_assignment.data.local.dao.CardDao
import com.yongjincompany.android_assignment.data.mapper.toCardEntity
import com.yongjincompany.android_assignment.data.mapper.toCardResponse
import com.yongjincompany.android_assignment.data.remote.api.CardApi
import com.yongjincompany.android_assignment.data.remote.model.response.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val cardApi: CardApi,
    private val cardDao: CardDao
) {
    fun fetchAllCardList(): Flow<List<Card>> = flow {
        runCatching {
            cardApi.fetchAllCardList()
        }.onSuccess {
            emit(it)
            cardDao.insertCards(it.map { card -> card.toCardEntity() })
        }.onFailure {
            val localData = cardDao.selectAllFromCardsTable().map { it.toCardResponse() }
            emit(localData)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun fetchRecentReadCardList(): Result<List<Card>> =
        kotlin.runCatching { cardApi.fetchRecentReadCardList() }

    suspend fun changeCardReadStatus(
        id: Long,
        isRead: Boolean
    ): Result<Card> =
        kotlin.runCatching { cardApi.changeCardReadStatus(id, isRead) }
}