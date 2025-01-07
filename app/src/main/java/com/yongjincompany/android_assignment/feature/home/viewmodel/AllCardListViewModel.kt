package com.yongjincompany.android_assignment.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.android_assignment.data.remote.model.response.Card
import com.yongjincompany.android_assignment.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCardListViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {
    private val _allCardList = MutableStateFlow<List<Card>>(emptyList())
    val allCardList = _allCardList.asStateFlow()

    fun fetchAllCardList() {
        viewModelScope.launch() {
            cardRepository.fetchAllCardList()
                .collect {
                    _allCardList.value = it
                }
        }
    }
}