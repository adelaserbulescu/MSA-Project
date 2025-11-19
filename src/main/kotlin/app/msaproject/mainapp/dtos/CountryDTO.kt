package app.msaproject.mainapp.dtos

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class CountryDTO(
    val countryId: String,
    val roadmapGroupId: String? = null,
    val name: String,
    val dateStarted: LocalDate? = null,
    val dateEnded: LocalDate? = null,
    val stillExists: Boolean,
    val flagImagePath: String? = null,
    val flagEmoji: String? = null,
    val hexColor: String? = null
)
