package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.repositories.interfaces.HtmlContentRepository
import app.msaproject.mainapp.dtos_mocks.htmlcontent.MockedHtmlContentData
import app.msaproject.mainapp.utils.PaginationUtils
import app.msaproject.mainapp.configs.PaginationConfig

class HtmlContentRepositoryImpl : HtmlContentRepository {

    private val list = MockedHtmlContentData.htmlContents

    override suspend fun getAll(): List<HtmlContentFullDTO> = list

    override suspend fun getById(id: Int): HtmlContentFullDTO? =
        list.find { it.htmlContentID == id }

    override suspend fun getFiltered(
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
    ): PaginatedResponseDTO<HtmlContentFullDTO> {

        var result = list.filter { c ->
            (countryID == null || c.countryID == countryID) &&
                    (contentType == null || c.contentType?.name.equals(contentType, ignoreCase = true)) &&
                    (version == null || c.version == version) &&
                    (pageIndex == null || c.pageIndex == pageIndex) &&
                    (pageSource == null || c.pageSource.equals(pageSource, ignoreCase = true)) &&
                    (after == null || c.dateAdded >= after) &&
                    (before == null || c.dateAdded <= before)
        }

        // Special mode: keep only latest version per (countryID + pageSource + pageIndex + contentType)
        if (latestOnly) {
            result = result
                .groupBy { g ->
                    listOf(
                        g.countryID,
                        g.pageSource,
                        g.pageIndex,
                        g.contentType
                    )
                }
                .map { (_, entries) -> entries.maxBy { it.version ?: 0 } }
                .sortedBy { it.htmlContentID }
        }

        // Sorting
        if (sort != null) {
            result = when (sort) {
                "htmlContentID" -> result.sortedBy { it.htmlContentID }
                "countryID" -> result.sortedBy { it.countryID }
                "dateAdded" -> result.sortedBy { it.dateAdded }
                else -> result
            }
            if (order == "desc") result = result.reversed()
        }

        return PaginationUtils.paginate(
            list = result,
            page = page,
            pageSize = PaginationConfig.htmlContentPageLimit
        )
    }
}
