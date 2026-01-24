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

object CountryGroupEntity : Table("CountryGroups") {

    val groupID = integer("groupID").autoIncrement()
    val groupName = varchar("groupName", 256)
    val groupDescription = varchar("groupDescription", 1024).nullable()
    val groupType = enumerationByName<GroupType>("groupType", 256).default(GroupType.OTHER)
    val dateStarted = date("DateStarted").nullable()
    val dateEnded = date("DateEnded").nullable()
    val stillExists = bool("StillExists")

    override val primaryKey = PrimaryKey(groupID)
}