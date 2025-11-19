package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date

enum class MediaType {
    IMAGE,
    LINK,
    ROUTE,
    OTHERS
}

object Media : Table("Media") {

    val mediaID = varchar("MediaID", 64)
    val countryID = varchar("CountryID", 64).references(Countries.countryId)
    val mediaType = enumerationByName<MediaType>("MediaType", 32).nullable()
    val mediaPath = varchar("MediaPath", 512).nullable()

    override val primaryKey = PrimaryKey(mediaID)
}