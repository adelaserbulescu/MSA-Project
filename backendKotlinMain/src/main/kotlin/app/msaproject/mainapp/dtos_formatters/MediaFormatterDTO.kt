package app.msaproject.mainapp.dtos_formatters

import app.msaproject.mainapp.dtos.media.MediaFullDTO
import app.msaproject.mainapp.entities.MediaEntity
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toMediaFullDTO(): MediaFullDTO {
    return MediaFullDTO(
        mediaID = this[MediaEntity.mediaID],
        countryID = this[MediaEntity.countryID],
        mediaType = this[MediaEntity.mediaType],
        mediaPath = this[MediaEntity.mediaPath]
    )
}
