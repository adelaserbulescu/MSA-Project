package app.msaproject.mainapp.dtos.country

import kotlinx.serialization.Serializable

@Serializable
data class CountrySimpleDTO(
    val countryID: Int,
    val name: String,
    val flagImagePath: String? = null,
    val hexColor: String? = null
)