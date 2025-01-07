package com.yongjincompany.android_assignment.data.di

import com.yongjincompany.android_assignment.data.local.dao.CardDao
import com.yongjincompany.android_assignment.data.remote.api.CardApi
import com.yongjincompany.android_assignment.data.repository.CardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCardRepository(cardApi: CardApi, cardDao: CardDao): CardRepository =
        CardRepository(cardApi, cardDao)
}