package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.core.util.repeatOnLifecycleState
import com.yongjincompany.android_assignment.core.util.toast
import com.yongjincompany.android_assignment.data.RetrofitBuilder
import com.yongjincompany.android_assignment.databinding.ActivityCardDetailBinding
import kotlinx.coroutines.launch

class CardDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val receivedCardId = intent.getIntExtra("card_id", 0)
        val receivedCardName = intent.getStringExtra("card_name")
        val receivedCardImg = intent.getIntExtra("card_img", 0)
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
        binding.ivCardImg.setImageResource(receivedCardImg)
        binding.tvCardGrade.text = receivedCardGrade
        binding.tvCardDescription.text = receivedCardDescription

        binding.ivBack.setOnClickListener {
            finish()
        }

    }
}