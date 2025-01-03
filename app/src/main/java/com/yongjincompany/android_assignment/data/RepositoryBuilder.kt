package com.yongjincompany.android_assignment.data

import com.yongjincompany.android_assignment.data.RetrofitBuilder.cardApi
import com.yongjincompany.android_assignment.data.repository.CardRepository

object RepositoryBuilder {
    val cardRepository = CardRepository(cardApi)
}