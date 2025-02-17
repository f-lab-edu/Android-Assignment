package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yongjincompany.android_assignment.core.util.repeatOnLifecycleState
import com.yongjincompany.android_assignment.core.util.toast
import com.yongjincompany.android_assignment.databinding.ActivityCardDetailBinding
import com.yongjincompany.android_assignment.feature.home.viewmodel.CardDetailFailed
import com.yongjincompany.android_assignment.feature.home.viewmodel.CardDetailViewModel
import com.yongjincompany.android_assignment.feature.home.viewmodel.ChangeCardReadStatusSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardDetailBinding
    private val vm : CardDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
        observe()
    }

    private fun init() {
        val receivedCardId = intent.getLongExtra("card_id", 0)

        vm.changeCardReadStatus(
            receivedCardId,
            true
        )

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun observe() {
        repeatOnLifecycleState {
            vm.event.collect {
                when(it) {
                    is ChangeCardReadStatusSuccess -> {
                        binding.tvCardId.text = it.data.id.toString()
                        binding.tvCardName.text = it.data.name
                        binding.tvCardGrade.text = it.data.grade.toString()
                        binding.tvCardDescription.text = it.data.description
                        Glide.with(this@CardDetailActivity)
                            .load(it.data.imageUrl)
                            .into(binding.ivCardImg)
                    }

                    is CardDetailFailed -> {
                        baseContext.toast(getString(it.errorMessage))
                    }
                }
            }
        }
    }
}