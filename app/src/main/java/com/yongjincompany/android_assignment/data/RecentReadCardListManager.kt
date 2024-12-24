package com.yongjincompany.android_assignment.data

object RecentReadCardListManager {
    private const val NOT_CONTAIN_SUCH_ELEMENT = -1

    private val _recentReadCardList = mutableListOf<Card>()
    val recentReadCardList
        get() = _recentReadCardList.toList()

    fun addRecentReadCard(card: Card) {
        val sameCardElementIndex = _recentReadCardList.indexOfFirst { it.id == card.id }

        if (sameCardElementIndex == NOT_CONTAIN_SUCH_ELEMENT) {
            _recentReadCardList.add(card)
        } else {
            _recentReadCardList.removeAt(sameCardElementIndex)
            _recentReadCardList.add(0, card)
        }
    }

    fun removeRecentFirstReadCard(block: () -> Unit) {
        if (_recentReadCardList.isNotEmpty()) {
            _recentReadCardList.removeAt(0)
            block()
        }
    }
}