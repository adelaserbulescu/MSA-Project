package app.msaproject.mainapp.services

import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.repositories.interfaces.HtmlContentRepository

class HtmlContentService(private val repository: HtmlContentRepository) {

    suspend fun getAll(): List<HtmlContentFullDTO> = repository.getAll()

    suspend fun getById(id: Int) = repository.getById(id)

    suspend fun getFiltered(
        countryID: Int?,
        contentType: String?,
        version: Int?,
        pageIndex: Int?,
        pageSource: String?,
        after: String?,
        before: String?,
        latestOnly: Boolean,
        page: Int?,
        sort: String?,
        order: String?
    ): PaginatedResponseDTO<HtmlContentFullDTO> =
        repository.getFiltered(
            countryID, contentType, version, pageIndex, pageSource,
            after, before, latestOnly, page, sort, order
        )
}
