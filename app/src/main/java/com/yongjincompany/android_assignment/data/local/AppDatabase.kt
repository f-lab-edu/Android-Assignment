package com.yongjincompany.android_assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yongjincompany.android_assignment.data.local.converter.CardConverter
import com.yongjincompany.android_assignment.data.local.dao.CardDao
import com.yongjincompany.android_assignment.data.local.entity.CardEntity

@Database(entities = [CardEntity::class], version = 1)
@TypeConverters(CardConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}