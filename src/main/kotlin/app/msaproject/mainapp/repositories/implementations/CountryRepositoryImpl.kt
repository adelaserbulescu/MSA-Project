package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.dtos.CountryDTO
import app.msaproject.mainapp.repositories.interfaces.CountryRepository
import kotlinx.coroutines.delay

class CountryRepositoryImpl : CountryRepository {

    private val fakeCountries = listOf(
        CountryDTO(
            countryId = "USA",
            roadmapGroupId = "G1",
            name = "United States",
            stillExists = true,
            flagEmoji = "🇺🇸",
            hexColor = "#FF0000"
        ),
        CountryDTO(
            countryId = "DE",
            roadmapGroupId = "G2",
            name = "Germany",
            stillExists = true,
            flagEmoji = "🇩🇪",
            hexColor = "#000000"
        )
    )

    override suspend fun getAll(): List<CountryDTO> {
        return fakeCountries
    }

    override suspend fun getById(id: String): CountryDTO? {
        return fakeCountries.find { it.countryId == id }
    }
}