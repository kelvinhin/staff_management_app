package com.example.staffmanagementapp.view

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.staffmanagementapp.model.StaffListPagingSource
import com.example.staffmanagementapp.repository.StaffManagementRepository

class StaffListViewModel : BaseViewModel() {
    val staffListFlow = Pager(PagingConfig(pageSize = 6)) {
        StaffListPagingSource(StaffManagementRepository.staffManagementApi)
    }.flow.cachedIn(viewModelScope)
}