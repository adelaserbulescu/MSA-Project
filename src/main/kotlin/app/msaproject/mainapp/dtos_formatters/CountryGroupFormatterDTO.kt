package app.msaproject.mainapp.dtos_formatters

import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullDTO
import app.msaproject.mainapp.entities.CountryEntity
import app.msaproject.mainapp.entities.CountryGroupEntity
import org.jetbrains.exposed.sql.ResultRow
import java.time.format.DateTimeFormatter

fun ResultRow.toCountryGroupFullDTO(): CountryGroupFullDTO {
    return CountryGroupFullDTO(
        groupID = this[CountryGroupEntity.groupID],
        groupName = this[CountryGroupEntity.groupName],
        groupDescription = this[CountryGroupEntity.groupDescription],
        groupType = this[CountryGroupEntity.groupType],
        dateStarted = this[CountryGroupEntity.dateStarted]?.format(DateTimeFormatter.ISO_LOCAL_DATE),
        dateEnded = this[CountryGroupEntity.dateEnded]?.format(DateTimeFormatter.ISO_LOCAL_DATE),
        stillExists = this[CountryEntity.stillExists],
    )
}