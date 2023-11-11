package com.example.staffmanagementapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StaffListResponse(
    val page: Int? = null,
    @SerialName("per_page")
    val perPage: Int? = null,
    val total: Int? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    val data: List<StaffData>? = null
) {
    @Serializable
    data class StaffData(
        val id: Int,
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        val avatar: String
    )
}
