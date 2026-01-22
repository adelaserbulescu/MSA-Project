package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*

enum class MediaType {
    IMAGE,
    PAGE,
    VIDEO,
    ROUTE,
    OTHER
}

object MediaEntity : Table("Media") {

    val mediaID = integer("MediaID").autoIncrement()
    val countryID = integer("CountryID").references(CountryEntity.countryID)
    val mediaType = enumerationByName<MediaType>("MediaType", 256).default(MediaType.OTHER)
    val mediaPath = varchar("MediaPath", 512)

    override val primaryKey = PrimaryKey(mediaID)
}