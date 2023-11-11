package com.example.staffmanagementapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.staffmanagementapp.data.response.StaffListResponse.StaffData
import com.example.staffmanagementapp.databinding.ItemStaffBinding

class StaffListItemAdapter(private val onItemClick: (StaffData?) -> Unit): PagingDataAdapter<StaffData, StaffListItemAdapter.StaffListItemViewHolder>(DiffCallback) {

    class StaffListItemViewHolder(private var binding: ItemStaffBinding, onItemClicked: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }

        fun bind (result: StaffData?) {
            binding.item = result
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<StaffData>() {
        override fun areItemsTheSame(
            oldItem: StaffData,
            newItem: StaffData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: StaffData,
            newItem: StaffData
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffListItemViewHolder {
        return StaffListItemViewHolder(ItemStaffBinding.inflate(LayoutInflater.from(parent.context))) {
            onItemClick(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: StaffListItemViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }
}