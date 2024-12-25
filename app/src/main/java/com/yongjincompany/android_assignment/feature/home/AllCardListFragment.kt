package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.core.util.SpacingItemDecoration
import com.yongjincompany.android_assignment.core.util.repeatOnLifecycleState
import com.yongjincompany.android_assignment.core.util.toast
import com.yongjincompany.android_assignment.data.RetrofitBuilder
import com.yongjincompany.android_assignment.databinding.FragmentAllCardListBinding
import com.yongjincompany.android_assignment.feature.home.adapter.AllCardListAdapter

class AllCardListFragment : Fragment(R.layout.fragment_all_card_list) {
    private var _binding: FragmentAllCardListBinding? = null
    private val binding get() = _binding!!
    private lateinit var allCardListAdapter: AllCardListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllCardListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allCardListAdapter = AllCardListAdapter()
        binding.rvAllCardList.adapter = allCardListAdapter
        binding.rvAllCardList.addItemDecoration(SpacingItemDecoration(30))

        repeatOnLifecycleState {
            runCatching {
                RetrofitBuilder.cardApi.fetchAllCardList()
            }.onSuccess {
                allCardListAdapter.updateAllCard(it)
            }.onFailure {
                it.printStackTrace()
                requireContext().toast(getString(R.string.cant_all_card_read))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

