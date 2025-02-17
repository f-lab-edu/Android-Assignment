package com.yongjincompany.android_assignment.data.remote.api

import com.yongjincompany.android_assignment.data.remote.model.response.Card
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface CardApi {
    @GET("card")
    suspend fun fetchAllCardList(): List<Card>

    @GET("card/read")
    suspend fun fetchRecentReadCardList(): List<Card>

    @PATCH("card/read/{id}")
    suspend fun changeCardReadStatus(
        @Path("id") id: Long,
        @Query("isRead") isRead: Boolean
    ): Card
}