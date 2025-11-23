package app.msaproject.mainapp.dtos.country

import kotlinx.serialization.Serializable

@Serializable
data class CountryFullDTO(
    val countryID: Int,
    val groupID: Int,
    val name: String,
    val dateStarted: String? = null,
    val dateEnded: String? = null,
    val stillExists: Boolean,
    val flagImagePath: String? = null,
    val hexColor: String? = null
)