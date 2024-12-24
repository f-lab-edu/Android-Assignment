package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.core.util.SpacingItemDecoration
import com.yongjincompany.android_assignment.core.util.repeatOnLifecycleState
import com.yongjincompany.android_assignment.core.util.toast
import com.yongjincompany.android_assignment.data.RetrofitBuilder
import com.yongjincompany.android_assignment.databinding.FragmentRecentReadCardListBinding
import com.yongjincompany.android_assignment.feature.home.adapter.RecentReadCardListAdapter
import kotlinx.coroutines.delay

class RecentReadCardListFragment : Fragment(R.layout.fragment_recent_read_card_list) {
    private var _binding: FragmentRecentReadCardListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recentReadCardListAdapter: RecentReadCardListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentReadCardListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recentReadCardListAdapter = RecentReadCardListAdapter()
        binding.rvRecentReadCardList.adapter = recentReadCardListAdapter
        binding.rvRecentReadCardList.addItemDecoration(SpacingItemDecoration(30))

        repeatOnLifecycleState {
            runCatching {
                RetrofitBuilder.cardApi.fetchRecentReadCardList()
            }.onSuccess {
                recentReadCardListAdapter.submitList(it)
            }.onFailure {
                requireContext().toast(getString(R.string.cant_fetch_card_list))
            }
        }

        repeatOnLifecycleState(Lifecycle.State.RESUMED) {
            while (true) {
                delay(10000)
                if (recentReadCardListAdapter.currentList.isNotEmpty()) {
                    runCatching {
                        RetrofitBuilder.cardApi.changeCardReadStatus(
                            recentReadCardListAdapter.currentList[0].id,
                            false
                        )
                    }.onSuccess {
                        recentReadCardListAdapter.removeFirstItem()
                        requireContext().toast(getString(R.string.remove_card_notice))
                    }.onFailure {
                        requireContext().toast(getString(R.string.cant_save_card_read))
                    }
                } else {
                    break
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}