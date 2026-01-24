package app.msaproject.mainapp.dtos.countrygroup

import app.msaproject.mainapp.entities.GroupType
import kotlinx.serialization.Serializable

@Serializable
data class CountryGroupFullDTO(
    val groupID: Int,
    val groupName: String,
    val groupDescription: String? = null,
    val groupType: GroupType? = null,
    val dateStarted: String? = null,
    val dateEnded: String? = null,
    val stillExists: Boolean? = null,
)