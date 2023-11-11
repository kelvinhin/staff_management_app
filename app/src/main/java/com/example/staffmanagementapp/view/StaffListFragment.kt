package com.example.staffmanagementapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.staffmanagementapp.R
import com.example.staffmanagementapp.adapter.StaffListItemAdapter
import com.example.staffmanagementapp.databinding.FragmentStaffListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StaffListFragment : Fragment() {
    private val staffListViewModel: StaffListViewModel by viewModels()
    private lateinit var binding: FragmentStaffListBinding
    private val navArgs by navArgs<StaffListFragmentArgs>()
    private lateinit var staffListAdapter: StaffListItemAdapter
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
        binding.rvStaffList.adapter = staffListAdapter
        lifecycleScope.launch {
            staffListViewModel.flow.collectLatest { pagingData ->
                staffListAdapter.submitData(pagingData)
            }
        }
    }

}