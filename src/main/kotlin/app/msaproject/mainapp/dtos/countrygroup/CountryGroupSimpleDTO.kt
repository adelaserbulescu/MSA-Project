package app.msaproject.mainapp.dtos.countrygroup

import kotlinx.serialization.Serializable

@Serializable
data class CountryGroupSimpleDTO(
    val groupID: Int,
    val groupName: String,
    val groupDescription: String? = null,
)