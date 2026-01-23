package app.msaproject.mainapp.repositories.interfaces

import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullDTO
import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullPostDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO

interface HtmlContentRepository {

    suspend fun getAll(): List<HtmlContentFullDTO>

    suspend fun getById(id: Int): HtmlContentFullDTO?

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
    ): PaginatedResponseDTO<HtmlContentFullDTO>

    suspend fun create(dto: HtmlContentFullPostDTO): Int
    suspend fun update(id: Int, dto: HtmlContentFullPostDTO): Boolean
    suspend fun delete(id: Int): Boolean
}
