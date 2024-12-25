package com.yongjincompany.android_assignment.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yongjincompany.android_assignment.data.model.response.Card
import com.yongjincompany.android_assignment.databinding.ItemCardBinding

class RecentReadCardListAdapter : ListAdapter<Card, CardViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding, {})
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

    fun removeFirstItem() {
        val currentList = currentList.toMutableList()

        if (currentList.isNotEmpty()) {
            currentList.removeAt(0)
            submitList(currentList)
        }
    }
}