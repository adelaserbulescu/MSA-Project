package app.msaproject.mainapp.dtos.formatters

import app.msaproject.mainapp.dtos.CountryDTO
import app.msaproject.mainapp.entities.Countries
import org.jetbrains.exposed.sql.ResultRow
import java.time.format.DateTimeFormatter

fun ResultRow.toCountryDTO(): CountryDTO {
    return CountryDTO(
        countryID = this[Countries.countryID],
        groupID = this[Countries.groupID],
        name = this[Countries.name],
        dateStarted = this[Countries.dateStarted]?.format(DateTimeFormatter.ISO_LOCAL_DATE),
        dateEnded = this[Countries.dateEnded]?.format(DateTimeFormatter.ISO_LOCAL_DATE),
        stillExists = this[Countries.stillExists],
        flagImagePath = this[Countries.flagImagePath],
        flagEmoji = this[Countries.flagEmoji],
        hexColor = this[Countries.hexColor]
    )
}