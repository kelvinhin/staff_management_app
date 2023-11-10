package com.example.staffmanagementapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.staffmanagementapp.R
import com.example.staffmanagementapp.databinding.FragmentStaffListBinding

class StaffListFragment : Fragment() {
    private val staffListViewModel: StaffListViewModel by viewModels()
    private lateinit var binding: FragmentStaffListBinding
    private val navArgs by navArgs<StaffListFragmentArgs>()
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
        binding.txvToken.text = navArgs.token
    }

}