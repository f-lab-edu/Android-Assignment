package com.yongjincompany.android_assignment.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yongjincompany.android_assignment.core.util.SpacingItemDecoration
import com.yongjincompany.android_assignment.core.util.repeatOnLifecycleState
import com.yongjincompany.android_assignment.data.RepositoryBuilder
import com.yongjincompany.android_assignment.databinding.FragmentAllCardListBinding
import com.yongjincompany.android_assignment.feature.home.adapter.AllCardListAdapter
import com.yongjincompany.android_assignment.feature.home.viewmodel.AllCardListViewModel
import com.yongjincompany.android_assignment.feature.home.viewmodel.AllCardListViewModelFactory

class AllCardListFragment : Fragment() {
    private var _binding: FragmentAllCardListBinding? = null
    private val binding get() = _binding!!
    private lateinit var allCardListAdapter: AllCardListAdapter
    private lateinit var vm: AllCardListViewModel

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

        init()
        observe()
    }

    private fun init() {
        val viewModelFactory = AllCardListViewModelFactory(RepositoryBuilder.cardRepository)
        vm = ViewModelProvider(this, viewModelFactory)[AllCardListViewModel::class.java]

        allCardListAdapter = AllCardListAdapter()
        binding.rvAllCardList.adapter = allCardListAdapter
        binding.rvAllCardList.addItemDecoration(SpacingItemDecoration(30))

        vm.fetchAllCardList()
    }

    private fun observe() {
        repeatOnLifecycleState {
            vm.allCardList.collect {
                allCardListAdapter.updateAllCard(vm.allCardList.value)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

