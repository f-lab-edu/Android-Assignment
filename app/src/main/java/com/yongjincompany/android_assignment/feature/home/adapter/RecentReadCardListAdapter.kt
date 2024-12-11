package com.yongjincompany.android_assignment.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yongjincompany.android_assignment.data.Card
import com.yongjincompany.android_assignment.R

class RecentReadCardListAdapter : ListAdapter<Card, CardViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view, {})
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(
                oldItem: Card,
                newItem: Card
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Card,
                newItem: Card
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}