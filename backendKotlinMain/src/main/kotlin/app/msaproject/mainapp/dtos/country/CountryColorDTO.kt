package app.msaproject.mainapp.dtos.country

import kotlinx.serialization.Serializable

@Serializable
data class CountryColorDTO(
    val countryID: Int,
    val hexColor: String? = null
)