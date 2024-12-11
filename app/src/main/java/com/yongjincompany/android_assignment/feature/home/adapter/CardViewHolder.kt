package com.yongjincompany.android_assignment.feature.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.data.Card

class CardViewHolder(itemView: View, private val listener: (Int) -> Unit) : ViewHolder(itemView) {
    val cardName: TextView
    val cardGrade: TextView
    val cardImg: ImageView

    init {
        cardName = itemView.findViewById(R.id.tv_card_name)
        cardGrade = itemView.findViewById(R.id.tv_card_grade)
        cardImg = itemView.findViewById(R.id.iv_card_img)

        itemView.setOnClickListener {
            listener(adapterPosition)
        }
    }

    fun bind(card: Card) {
        cardImg.setImageResource(card.img)
        cardName.text = card.name
        cardGrade.text = card.grade.name
    }
}