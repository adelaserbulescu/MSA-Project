package app.msaproject.mainapp.dtos_formatters

import app.msaproject.mainapp.dtos.country.CountryFullDTO
import app.msaproject.mainapp.entities.CountryEntity
import org.jetbrains.exposed.sql.ResultRow
import java.time.format.DateTimeFormatter

fun ResultRow.toCountryFullDTO(): CountryFullDTO {
    return CountryFullDTO(
        countryID = this[CountryEntity.countryID],
        groupID = this[CountryEntity.groupID],
        countryName = this[CountryEntity.countryName],
        dateStarted = this[CountryEntity.dateStarted]?.format(DateTimeFormatter.ISO_LOCAL_DATE),
        dateEnded = this[CountryEntity.dateEnded]?.format(DateTimeFormatter.ISO_LOCAL_DATE),
        stillExists = this[CountryEntity.stillExists],
        flagImagePath = this[CountryEntity.flagImagePath],
        hexColor = this[CountryEntity.hexColor]
    )
}