package app.msaproject.mainapp.dtos_formatters

import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullDTO
import app.msaproject.mainapp.entities.CountryEntity
import app.msaproject.mainapp.entities.HtmlContentEntity
import org.jetbrains.exposed.sql.ResultRow
import java.time.format.DateTimeFormatter

fun ResultRow.toHtmlContentFullDTO(): HtmlContentFullDTO {
    return HtmlContentFullDTO(
        htmlContentID = this[HtmlContentEntity.htmlContentID],
        countryID = this[HtmlContentEntity.countryID],
        contentHtml = this[HtmlContentEntity.contentHtml],
        contentType = this[HtmlContentEntity.contentType],
        version = this[HtmlContentEntity.version],
        pageIndex = this[HtmlContentEntity.pageIndex],
        pageSource = this[HtmlContentEntity.pageSource],
        dateAdded = this[HtmlContentEntity.dateAdded].format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
    )
}