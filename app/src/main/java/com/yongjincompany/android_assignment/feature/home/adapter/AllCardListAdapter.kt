package com.yongjincompany.android_assignment.feature.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.android_assignment.data.model.response.Card
import com.yongjincompany.android_assignment.databinding.ItemCardBinding
import com.yongjincompany.android_assignment.feature.home.CardDetailActivity

class AllCardListAdapter(private var data: List<Card> = emptyList()) :
    RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CardViewHolder(binding, {
            val cardData = data[it]

            val intent = Intent(parent.context, CardDetailActivity::class.java)
            intent.apply {
                putExtra("card_id", cardData.id)
                putExtra("card_name", cardData.name)
                putExtra("card_img", cardData.imageUrl)
                putExtra("card_grade", cardData.grade.name)
                putExtra("card_description", cardData.description)
            }
            parent.context.startActivity(intent)
        })
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun updateAllCard(cardList: List<Card>) {
        this.data = cardList
        notifyDataSetChanged()
    }
}
