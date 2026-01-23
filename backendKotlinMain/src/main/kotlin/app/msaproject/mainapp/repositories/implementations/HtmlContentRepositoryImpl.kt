package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.dtos_formatters.toHtmlContentFullDTO
import app.msaproject.mainapp.entities.HtmlContentEntity
import app.msaproject.mainapp.entities.HtmlContentType
import app.msaproject.mainapp.repositories.interfaces.HtmlContentRepository
import app.msaproject.mainapp.utils.PaginationUtils
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

class HtmlContentRepositoryImpl : HtmlContentRepository {

    override suspend fun getAll(): List<HtmlContentFullDTO> =
        transaction {
            HtmlContentEntity.selectAll().map { it.toHtmlContentFullDTO() }
        }

    override suspend fun getById(id: Int): HtmlContentFullDTO? =
        transaction {
            HtmlContentEntity
                .select { HtmlContentEntity.htmlContentID eq id }
                .map { it.toHtmlContentFullDTO() }
                .singleOrNull()
        }

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

        return transaction {

            val query = HtmlContentEntity.selectAll()

            if (countryID != null)
                query.andWhere { HtmlContentEntity.countryID eq countryID }

            val typeEnum = contentType?.let { HtmlContentType.valueOf(it.uppercase()) }
            if (typeEnum != null)
                query.andWhere { HtmlContentEntity.contentType eq typeEnum }

            if (version != null)
                query.andWhere { HtmlContentEntity.version eq version }

            if (pageIndex != null)
                query.andWhere { HtmlContentEntity.pageIndex eq pageIndex }

            if (pageSource != null)
                query.andWhere { HtmlContentEntity.pageSource eq pageSource }

            val afterD = after?.let { LocalDate.parse(it) }
            if (afterD != null)
                query.andWhere { HtmlContentEntity.dateAdded greaterEq afterD }

            val beforeD = before?.let { LocalDate.parse(it) }
            if (beforeD != null)
                query.andWhere { HtmlContentEntity.dateAdded lessEq beforeD }


            var list = query.map { it.toHtmlContentFullDTO() }

            if (latestOnly) {
                list = list
                    .groupBy { Triple(it.countryID, it.pageIndex, it.contentType) }
                    .map { (_, entries) -> entries.maxBy { it.version ?: 0 } }
                    .sortedBy { it.htmlContentID }
            }

            list = when (sort) {
                "htmlContentID" -> list.sortedBy { it.htmlContentID }
                "countryID"     -> list.sortedBy { it.countryID }
                "dateAdded"     -> list.sortedBy { it.dateAdded }
                else -> list
            }

            if (order == "desc") list = list.reversed()

            PaginationUtils.paginate(
                list = list,
                page = page,
                pageSize = PaginationConfig.htmlContentPageLimit
            )
        }
    }
}
