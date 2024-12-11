package com.yongjincompany.android_assignment.data

object RecentReadCardListManager {
    private val _recentReadList = mutableListOf<Card>()
    val recentReadList
        get() = _recentReadList.toList()

    fun addRecentReadCard(card: Card) {
        val sameCardElementIndex = _recentReadList.indexOfFirst { it.id == card.id }
        val NOT_CONTAIN_SUCH_ELEMENT = -1

        if (sameCardElementIndex == NOT_CONTAIN_SUCH_ELEMENT) {
            _recentReadList.add(card)
        } else {
            _recentReadList.removeAt(sameCardElementIndex)
            _recentReadList.add(0, card)
        }
    }

    fun removeRecentFirstReadCard(block: () -> Unit) {
        if (_recentReadList.isNotEmpty()) {
            _recentReadList.removeAt(0)
            block()
        }
    }
}