package com.example.staffmanagementapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StaffListResponse(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("per_page")
    val perPage: Int? = null,
    @SerialName("total")
    val total: Int? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("data")
    val data: Data? = null
) {
    data class Data(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("email")
        val email: String? = null,
        @SerialName("first_name")
        val firstName: String? = null,
        @SerialName("last_name")
        val lastName: String? = null,
        @SerialName("avatar")
        val avatar: String? = null
    )
}
