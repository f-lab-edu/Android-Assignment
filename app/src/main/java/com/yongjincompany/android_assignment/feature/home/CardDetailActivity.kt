package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.yongjincompany.android_assignment.R

class CardDetailActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        val cardId: TextView = findViewById(R.id.detail_tv_card_id)
        val cardName: TextView = findViewById(R.id.detail_tv_card_name)
        val cardImg: ImageView = findViewById(R.id.detail_iv_card_img)
        val cardGrade: TextView = findViewById(R.id.detail_tv_card_grade)
        val cardDescription: TextView = findViewById(R.id.detail_tv_card_description)

        val backBtn: ImageView = findViewById(R.id.detail_iv_back)

        val receivedCardId = intent.getIntExtra("card_id", 0)
        val receivedCardName = intent.getStringExtra("card_name")
        val receivedCardImg = intent.getIntExtra("card_img", 0)
        val receivedCardGrade = intent.getStringExtra("card_grade")
        val receivedCardDescription = intent.getStringExtra("card_description")

        cardId.text = receivedCardId.toString()
        cardName.text = receivedCardName
        cardImg.setImageResource(receivedCardImg)
        cardGrade.text = receivedCardGrade
        cardDescription.text = receivedCardDescription

        backBtn.setOnClickListener {
            finish()
        }

    }
}