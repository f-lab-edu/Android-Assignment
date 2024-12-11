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
            RecentReadCardListManager.addRecentReadCard(data[it])

            val intent = Intent(parent.context, CardDetailActivity::class.java)
            intent.putExtra("card_id", data[it].id)
            intent.putExtra("card_name", data[it].name)
            intent.putExtra("card_img", data[it].img)
            intent.putExtra("card_grade", data[it].grade.name)
            intent.putExtra("card_description", data[it].description)
            parent.context.startActivity(intent)
        })
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}
