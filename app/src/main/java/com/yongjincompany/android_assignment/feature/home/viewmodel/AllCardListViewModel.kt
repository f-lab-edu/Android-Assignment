package com.yongjincompany.android_assignment.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.android_assignment.data.local.dao.CardDao
import com.yongjincompany.android_assignment.data.mapper.toCardEntity
import com.yongjincompany.android_assignment.data.mapper.toCardResponse
import com.yongjincompany.android_assignment.data.remote.model.response.Card
import com.yongjincompany.android_assignment.data.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllCardListViewModel(
    private val cardRepository: CardRepository,
    private val cardDao: CardDao
) : ViewModel() {
    private val _allCardList = MutableStateFlow<List<Card>>(emptyList())
    val allCardList = _allCardList.asStateFlow()

    fun fetchAllCardList() {
        viewModelScope.launch(Dispatchers.IO) {
            cardRepository.fetchAllCardList()
                .onSuccess {
                    _allCardList.value = it
                    cardDao.insertCards(it.map { cardResponse -> cardResponse.toCardEntity() })
                }.onFailure {
                    _allCardList.value = cardDao.selectAllFromCardsTable().map { it.toCardResponse() }
                }
        }
    }
}