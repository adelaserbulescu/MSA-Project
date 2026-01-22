package app.msaproject.mainapp.dtos.pagination

import kotlinx.serialization.Serializable

@Serializable
data class PaginatedResponseDTO<T>(
    val items: List<T>,
    val totalItems: Int,
    val totalPages: Int,
    val currentPage: Int,
    val pageSize: Int
)