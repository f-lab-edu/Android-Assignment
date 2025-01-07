package com.yongjincompany.android_assignment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yongjincompany.android_assignment.data.local.converter.CardConverter
import com.yongjincompany.android_assignment.data.local.dao.CardDao
import com.yongjincompany.android_assignment.data.local.entity.CardEntity

@Database(entities = [CardEntity::class], version = 1)
@TypeConverters(CardConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}