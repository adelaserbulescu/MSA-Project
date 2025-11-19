package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date

object Countries : Table("Countries") {

    val countryId = integer("CountryID").autoIncrement()
    //val roadmapGroupId = integer("RoadMapGroupID").references() - Use to set up foreign key when you create other entities
    val name = varchar("Name", 256).uniqueIndex()
    val dateStarted = date("DateStarted").nullable()
    val dateEnded = date("DateEnded").nullable()
    val stillExists = bool("StillExists")
    val flagImagePath = varchar("FlagImagePath", 512).nullable()
    val flagEmoji = varchar("FlagEmoji", 16).nullable()
    val hexColor = varchar("HexColor", 16).nullable()

    override val primaryKey = PrimaryKey(countryId)
}
