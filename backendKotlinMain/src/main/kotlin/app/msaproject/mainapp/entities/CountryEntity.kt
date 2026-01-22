package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date

enum class CountryType {}

object CountryEntity : Table("Countries") {

    val countryID = integer("CountryID").autoIncrement();
    val groupID = integer("GroupID").references(CountryGroupEntity.groupID)
    val countryName = varchar("CountryName", 256).uniqueIndex()
    val dateStarted = date("DateStarted").nullable()
    val dateEnded = date("DateEnded").nullable()
    val stillExists = bool("StillExists")
    val flagImagePath = varchar("FlagImagePath", 512).nullable()
    val hexColor = varchar("HexColor", 16).nullable()

    override val primaryKey = PrimaryKey(countryID)
}
