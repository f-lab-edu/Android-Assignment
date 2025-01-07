package com.yongjincompany.android_assignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yongjincompany.android_assignment.data.local.entity.CardEntity

@Dao
interface CardDao {
    @Query("SELECT * FROM cards")
    suspend fun selectAllFromCardsTable(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCards(cards: List<CardEntity>)
}