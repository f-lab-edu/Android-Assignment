package com.yongjincompany.android_assignment.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.data.model.response.Card
import com.yongjincompany.android_assignment.data.repository.CardRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RecentReadCardListViewModel(private val cardRepository: CardRepository) : ViewModel() {
    private val _event = MutableSharedFlow<RecentReadCardListEvent>()
    val event = _event.asSharedFlow()

    fun fetchRecentReadCardList() {
        viewModelScope.launch {
            cardRepository.fetchRecentReadCardList()
                .onSuccess {
                    _event.emit(FetchRecentReadCardListSuccess(it))
                }.onFailure {
                    _event.emit(RecentReadCardListFailed(R.string.cant_fetch_card_list))
                }
        }
    }

    fun changeCardReadStatus(id: Long, isRead: Boolean) {
        viewModelScope.launch {
            cardRepository.changeCardReadStatus(
                id,
                isRead
            ).onSuccess {
                _event.emit(ChangeReadStatusSuccess)
            }.onFailure {
                _event.emit(RecentReadCardListFailed(R.string.cant_save_card_read))
            }
        }
    }
}

sealed interface RecentReadCardListEvent

data class FetchRecentReadCardListSuccess(val data: List<Card>) : RecentReadCardListEvent
data object ChangeReadStatusSuccess : RecentReadCardListEvent
data class RecentReadCardListFailed(val errorMessage: Int) : RecentReadCardListEvent

