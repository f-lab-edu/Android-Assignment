package com.yongjincompany.android_assignment.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yongjincompany.android_assignment.data.repository.CardRepository

class AllCardListViewModelFactory(private val cardRepository: CardRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllCardListViewModel(cardRepository) as T
    }
}