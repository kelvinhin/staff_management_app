package com.example.staffmanagementapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.staffmanagementapp.R
import com.example.staffmanagementapp.adapter.LoaderStateAdapter
import com.example.staffmanagementapp.adapter.StaffListItemAdapter
import com.example.staffmanagementapp.databinding.FragmentStaffListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StaffListFragment : Fragment() {
    private val staffListViewModel: StaffListViewModel by viewModels()
    private lateinit var binding: FragmentStaffListBinding
    private val navArgs by navArgs<StaffListFragmentArgs>()
    private lateinit var staffListAdapter: StaffListItemAdapter
    private lateinit var loaderStateAdapter: LoaderStateAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStaffListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = staffListViewModel
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.txvToken.text = getString(R.string.token_display, navArgs.token)
        staffListAdapter = StaffListItemAdapter { selectedResult ->
            // TODO handle on click
        }
        binding.btnRetry.setOnClickListener {
            binding.viewGroupRetry.visibility = View.GONE
            staffListAdapter.retry()
        }
        loaderStateAdapter = LoaderStateAdapter { staffListAdapter.retry() }
        staffListAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                staffListViewModel.showMainLoading()
            } else {
                staffListViewModel.hideMainLoading()

                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    binding.viewGroupRetry.visibility = View.VISIBLE
                }
            }
        }
        binding.rvStaffList.adapter = staffListAdapter.withLoadStateFooter(loaderStateAdapter)
        lifecycleScope.launch {
            staffListViewModel.staffListFlow.collectLatest { pagingData ->
                staffListAdapter.submitData(pagingData)
            }
        }
    }

}