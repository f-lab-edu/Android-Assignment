package com.yongjincompany.android_assignment.feature.home.adapter

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yongjincompany.android_assignment.data.model.response.Card
import com.yongjincompany.android_assignment.databinding.ItemCardBinding

class CardViewHolder(private val binding: ItemCardBinding, private val listener: (Int) -> Unit) : ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            listener(adapterPosition)
        }
    }

    fun bind(card: Card) {
        binding.ivCardImg.setImageURI(card.imageUrl.toUri())
        binding.tvCardName.text = card.name
        binding.tvCardGrade.text = card.grade.name
    }
}