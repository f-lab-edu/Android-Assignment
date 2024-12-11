package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.android_assignment.data.Card
import com.yongjincompany.android_assignment.data.Grade
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.core.util.SpacingItemDecoration
import com.yongjincompany.android_assignment.feature.home.adapter.AllCardListAdapter

class AllCardListFragment : Fragment(R.layout.fragment_all_card_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allCardListRecyclerView: RecyclerView = view.findViewById(R.id.rv_all_card_list)
        val allCardList = listOf(
            Card(id = 0, grade = Grade.D, name = "사죄 용진", img = R.drawable.sorry_yongjin_card_img, description = "왜 이 사람은 책상 위에 올라가 사죄하고 있을까요? 전 알아요 그는 세미콜론이라는 동아리를 나가서 새 길을 개척하겠다는 포부를 가지고 있다는 것을"),
            Card(id = 1, grade = Grade.C, name = "민달팽이 정민", img = R.drawable.snail_jeongmin_card_img, description = "문정민, 그는 프랭키 그 자체입니다. 그의 얼굴을 보고 절대 속으면 안됩니다. 끔찍하게도 그의 얼굴과 달리 그의 몸은 민달팽이와 같은 구조로 되어있습니다."),
            Card(id = 2, grade = Grade.B, name = "이별 영준", img = R.drawable.brakeup_youngjun_card_img, description = "만약 저런 표정으로 누워있는 그를 발견한다면 그를 절대로 건드리지 않는 것이 좋을겁니다. 그는 안좋은 일을 겪었거든요…"),
            Card(id = 3, grade = Grade.A, name = "마스터이 경수", img = R.drawable.masteryi_gyeongsu_card_img, description = "그는 잠자리일까요? 사람일까요? 그의 정체를 아는 사람은 없을겁니다… 아마도요.."),
            Card(id = 4, grade = Grade.A, name = "앙큼한 진성", img = R.drawable.cute_jinsung_card_img, description = "오우 그를 보셨나요? 그는 참 상큼발랄한 친구입니다. 그의 이름이요? 황진성입니다. 그는 웹개발자이죠.")
        )

        allCardListRecyclerView.adapter = AllCardListAdapter(allCardList)
        allCardListRecyclerView.addItemDecoration(SpacingItemDecoration(30))
    }
}

