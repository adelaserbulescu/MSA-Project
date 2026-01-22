package app.msaproject.mainapp.dtos.media

import app.msaproject.mainapp.entities.MediaType
import kotlinx.serialization.Serializable

@Serializable
data class MediaFullDTO(
    val mediaID: Int,
    val countryID: Int,
    val mediaType: MediaType? = null,
    val mediaPath: String
)