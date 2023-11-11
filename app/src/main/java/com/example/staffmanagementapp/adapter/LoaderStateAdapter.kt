package com.example.staffmanagementapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.staffmanagementapp.databinding.ItemStaffListLoaderBinding

class LoaderStateAdapter(private val retry: () -> Unit): LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {
    class LoaderViewHolder(private var binding: ItemStaffListLoaderBinding, retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Loading) {
                binding.viewGroupRetry.visibility = View.GONE
                binding.pbLoader.visibility = View.VISIBLE
            } else {
                binding.viewGroupRetry.visibility = View.VISIBLE
                binding.pbLoader.visibility = View.GONE
            }
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(ItemStaffListLoaderBinding.inflate(LayoutInflater.from(parent.context))) {
            retry
        }
    }
}