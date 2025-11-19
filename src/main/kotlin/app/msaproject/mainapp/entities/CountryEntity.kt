package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date

object Countries : Table("Countries") {

    val countryId = varchar("CountryID",64)
    val groupId = varchar("GroupID", 64).references(CountryGroups.groupID)
    val name = varchar("Name", 256).uniqueIndex()
    val dateStarted = date("DateStarted").nullable()
    val dateEnded = date("DateEnded").nullable()
    val stillExists = bool("StillExists")
    val flagImagePath = varchar("FlagImagePath", 512).nullable()
    val flagEmoji = varchar("FlagEmoji", 16).nullable()
    val hexColor = varchar("HexColor", 16).nullable()

    override val primaryKey = PrimaryKey(countryId)
}
