package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*

enum class MediaType {
    IMAGE,
    LINK,
    ROUTE,
    OTHERS
}

object Media : Table("Media") {

    val mediaID = integer("MediaID").autoIncrement()
    val countryID = integer("CountryID").references(Countries.countryID)
    val mediaType = enumerationByName<MediaType>("MediaType", 32).nullable()
    val mediaPath = varchar("MediaPath", 512).nullable()

    override val primaryKey = PrimaryKey(mediaID)
}