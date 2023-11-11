package com.example.staffmanagementapp.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.staffmanagementapp.api.StaffManagementApi
import com.example.staffmanagementapp.data.response.StaffListResponse
import retrofit2.HttpException
import java.io.IOException

class StaffListPagingSource(
    val backend: StaffManagementApi
): PagingSource<Int, StaffListResponse.StaffData>() {
    override fun getRefreshKey(state: PagingState<Int, StaffListResponse.StaffData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StaffListResponse.StaffData> {
        val requestPage = params.key ?: 1
        return try {
            val response = backend.getStaffList(requestPage)
            if (response.data != null) {
                val nextPage = if (response.page == response.totalPages) null else requestPage + 1
                LoadResult.Page(
                    data = response.data,
                    prevKey = null,
                    nextKey = nextPage
                )
            } else {
                LoadResult.Error(Exception("Response body invalid"))
            }
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}