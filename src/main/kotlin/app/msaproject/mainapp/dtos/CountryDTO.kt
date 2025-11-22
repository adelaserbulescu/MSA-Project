package app.msaproject.mainapp.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CountryDTO(
    val countryID: Int,
    val groupID: Int,
    val name: String,
    val dateStarted: String? = null,
    val dateEnded: String? = null,
    val stillExists: Boolean,
    val flagImagePath: String? = null,
    val flagEmoji: String? = null,
    val hexColor: String? = null
)
