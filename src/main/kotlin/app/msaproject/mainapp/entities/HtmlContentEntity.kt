package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date

object HtmlContents : Table("HtmlContents") {
    val htmlContentID = integer("HtmlContentID").autoIncrement()
    val countryID = integer("CountryID").references(Countries.countryID)
    val contentHtml = text("ContentHtml")
    val version = integer("Version").default(1)
    val pageIndex = integer("PageIndex").default(0)
    val dateAdded = date("DateAdded")

    override val primaryKey = PrimaryKey(htmlContentID)

    val uniqueVersionPerPage = uniqueIndex(
        "uniqueVersionPerPage",
        countryID, pageIndex, version
    )
}