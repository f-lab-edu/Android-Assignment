package com.yongjincompany.android_assignment.data.di

import com.yongjincompany.android_assignment.data.remote.api.CardApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideUsersApi(retrofit: Retrofit): CardApi = retrofit.create(CardApi::class.java)
}