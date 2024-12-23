package com.yongjincompany.android_assignment.feature.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.android_assignment.data.RecentReadCardListManager
import com.yongjincompany.android_assignment.data.Card
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.feature.home.CardDetailActivity

class AllCardListAdapter(private val data: List<Card>) :
    RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return CardViewHolder(view, {
            val cardData = data[it]
            RecentReadCardListManager.addRecentReadCard(cardData)

            val intent = Intent(parent.context, CardDetailActivity::class.java)
            intent.apply {
                putExtra("card_id", cardData.id)
                putExtra("card_name", cardData.name)
                putExtra("card_img", cardData.img)
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
}
