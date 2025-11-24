package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date

enum class HtmlContentType{
    MAIN,
    GENERAL,
    HISTORY,
    CULTURE,
    SCIENCE,
    EVENT,
    OTHER
}

object HtmlContentEntity : Table("HtmlContents") {
    val htmlContentID = integer("HtmlContentID").autoIncrement()
    val countryID = integer("CountryID").references(CountryEntity.countryID)
    val contentHtml = text("ContentHtml")
    val contentType = enumerationByName<HtmlContentType>("ContentType", 256).default(HtmlContentType.OTHER)
    val version = integer("Version").default(1)
    val pageIndex = integer("PageIndex").default(0)
    val pageSource = varchar("PageSource", 256).nullable()
    val dateAdded = date("DateAdded")

    override val primaryKey = PrimaryKey(htmlContentID)

    val uniqueVersionPerPage = uniqueIndex(
        "uniqueVersionPerPage",
        countryID, pageIndex, version
    )
}