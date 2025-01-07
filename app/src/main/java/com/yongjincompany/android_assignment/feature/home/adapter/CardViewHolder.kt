package com.yongjincompany.android_assignment.feature.home.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.yongjincompany.android_assignment.data.remote.model.response.Card
import com.yongjincompany.android_assignment.databinding.ItemCardBinding

class CardViewHolder(private val binding: ItemCardBinding, private val listener: (Int) -> Unit) : ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            listener(adapterPosition)
        }
    }

    fun bind(card: Card) {
        binding.tvCardName.text = card.name
        binding.tvCardGrade.text = card.grade.name
        Glide.with(binding.root.context)
            .load(card.imageUrl)
            .into(binding.ivCardImg)
    }
}