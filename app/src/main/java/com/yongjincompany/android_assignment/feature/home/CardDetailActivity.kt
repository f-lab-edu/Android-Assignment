package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.core.util.repeatOnLifecycleState
import com.yongjincompany.android_assignment.core.util.toast
import com.yongjincompany.android_assignment.data.RetrofitBuilder
import com.yongjincompany.android_assignment.databinding.ActivityCardDetailBinding

class CardDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val receivedCardId = intent.getIntExtra("card_id", 0)
        val receivedCardName = intent.getStringExtra("card_name")
        val receivedCardImg = intent.getStringExtra("card_img")
        val receivedCardGrade = intent.getStringExtra("card_grade")
        val receivedCardDescription = intent.getStringExtra("card_description")

        repeatOnLifecycleState {
            runCatching {
                RetrofitBuilder.cardApi.changeCardReadStatus(
                    receivedCardId.toLong(),
                    true
                )
            }.onFailure {
                baseContext.toast(getString(R.string.cant_save_card_read))
            }
        }

        binding.tvCardId.text = receivedCardId.toString()
        binding.tvCardName.text = receivedCardName
        binding.tvCardGrade.text = receivedCardGrade
        binding.tvCardDescription.text = receivedCardDescription
        Glide.with(this)
            .load(receivedCardImg)
            .into(binding.ivCardImg)

        binding.ivBack.setOnClickListener {
            finish()
        }

    }
}