package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.yongjincompany.android_assignment.R
import com.yongjincompany.android_assignment.core.util.SpacingItemDecoration
import com.yongjincompany.android_assignment.core.util.repeatOnLifecycleState
import com.yongjincompany.android_assignment.core.util.toast
import com.yongjincompany.android_assignment.data.RepositoryBuilder
import com.yongjincompany.android_assignment.databinding.FragmentRecentReadCardListBinding
import com.yongjincompany.android_assignment.feature.home.adapter.RecentReadCardListAdapter
import com.yongjincompany.android_assignment.feature.home.viewmodel.ChangeReadStatusSuccess
import com.yongjincompany.android_assignment.feature.home.viewmodel.FetchRecentReadCardListSuccess
import com.yongjincompany.android_assignment.feature.home.viewmodel.RecentReadCardListFailed
import com.yongjincompany.android_assignment.feature.home.viewmodel.RecentReadCardListViewModel
import com.yongjincompany.android_assignment.feature.home.viewmodel.RecentReadCardListViewModelFactory
import kotlinx.coroutines.delay

class RecentReadCardListFragment : Fragment() {
    private var _binding: FragmentRecentReadCardListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recentReadCardListAdapter: RecentReadCardListAdapter
    private lateinit var vm: RecentReadCardListViewModel

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

        init()
        observe()
    }

    private fun init() {
        val viewModelFactory = RecentReadCardListViewModelFactory(RepositoryBuilder.cardRepository)
        vm = ViewModelProvider(this, viewModelFactory)[RecentReadCardListViewModel::class.java]

        recentReadCardListAdapter = RecentReadCardListAdapter()
        binding.rvRecentReadCardList.adapter = recentReadCardListAdapter
        binding.rvRecentReadCardList.addItemDecoration(SpacingItemDecoration(30))

        vm.fetchRecentReadCardList()
    }

    private fun observe() {
        repeatOnLifecycleState {
            vm.event.collect {
                when (it) {
                    ChangeReadStatusSuccess -> {
                        recentReadCardListAdapter.removeFirstItem()
                        requireContext().toast(getString(R.string.remove_card_notice))
                    }

                    is FetchRecentReadCardListSuccess -> {
                        recentReadCardListAdapter.submitList(it.data)

                        repeatOnLifecycleState(Lifecycle.State.RESUMED) {
                            while (true) {
                                delay(10000)
                                if (recentReadCardListAdapter.currentList.isNotEmpty()) {
                                    vm.changeCardReadStatus(
                                        recentReadCardListAdapter.currentList[0].id,
                                        false
                                    )
                                } else {
                                    break
                                }
                            }
                        }
                    }

                    is RecentReadCardListFailed -> requireContext().toast(getString(it.errorMessage))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}