package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.android_assignment.data.RecentReadCardListManager
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.core.util.SpacingItemDecoration
import com.yongjincompany.android_assignment.feature.home.adapter.RecentReadCardListAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecentReadCardListFragment : Fragment(R.layout.fragment_recent_read_card_list) {
    private var recentReadCardListAdapter: RecentReadCardListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recentReadCardListRecyclerView: RecyclerView =
            view.findViewById(R.id.rv_recent_read_card_list)

        recentReadCardListAdapter = RecentReadCardListAdapter()
        recentReadCardListRecyclerView.adapter = recentReadCardListAdapter
        recentReadCardListRecyclerView.addItemDecoration(SpacingItemDecoration(30))

        recentReadCardListAdapter?.submitList(RecentReadCardListManager.recentReadCardList)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                while (true) {
                    delay(10000)
                    RecentReadCardListManager.removeRecentFirstReadCard(
                        {
                            recentReadCardListAdapter?.submitList(RecentReadCardListManager.recentReadCardList)
                            Toast
                                .makeText(
                                    requireContext(),
                                    getString(R.string.remove_card_notice),
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    )
                }
            }
        }
    }
}