package app.msaproject.mainapp.entities

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date

enum class GroupType {
    ALLIANCE,
    AREA,
    ROADMAP,
    OTHER,
    CUSTOM,
}

object CountryGroups : Table("CountryGroups") {

    val groupID = integer("groupID").autoIncrement()
    val groupName = varchar("groupName", 256)
    val groupDesc = varchar("groupDesc", 1024).nullable()
    val groupType = enumerationByName<GroupType>("groupType", 32).nullable()
    val dateStarted = date("DateStarted").nullable()
    val dateEnded = date("DateEnded").nullable()
    val stillExists = bool("StillExists")

    override val primaryKey = PrimaryKey(groupID)
}