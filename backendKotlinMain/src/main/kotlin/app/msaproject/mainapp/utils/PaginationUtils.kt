package app.msaproject.mainapp.utils

import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO

object PaginationUtils {

    fun <T> paginate(
        list: List<T>,
        page: Int?,
        pageSize: Int
    ): PaginatedResponseDTO<T> {

        val currentPage = page ?: 1
        val totalItems = list.size
        val totalPages = if (totalItems == 0) 1 else (totalItems + pageSize - 1) / pageSize

        val from = (currentPage - 1) * pageSize
        val to = minOf(from + pageSize, totalItems)

        val items = if (from < totalItems) list.subList(from, to) else emptyList()

        return PaginatedResponseDTO(
            items = items,
            totalItems = totalItems,
            totalPages = totalPages,
            currentPage = currentPage,
            pageSize = pageSize
        )
    }
}
