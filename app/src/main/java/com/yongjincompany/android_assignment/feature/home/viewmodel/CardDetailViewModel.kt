package com.yongjincompany.android_assignment.feature.home.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.data.model.response.Card
import com.yongjincompany.android_assignment.data.repository.CardRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CardDetailViewModel(private val cardRepository: CardRepository) : ViewModel() {
    private val _event = MutableSharedFlow<CardDetailEvent>()
    val event = _event.asSharedFlow()

    fun changeCardReadStatus(id: Long, isRead: Boolean) {
        viewModelScope.launch {
            cardRepository.changeCardReadStatus(
                id,
                isRead
            ).onSuccess {
                _event.emit(ChangeCardReadStatusSuccess(it))
            }.onFailure {
                _event.emit(CardDetailFailed(R.string.cant_save_card_read))
            }
        }
    }
}

sealed interface CardDetailEvent

data class ChangeCardReadStatusSuccess(val data: Card) : CardDetailEvent
data class CardDetailFailed(@StringRes val errorMessage: Int) : CardDetailEvent