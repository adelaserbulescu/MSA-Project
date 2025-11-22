package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.dtos.CountryDTO
import app.msaproject.mainapp.repositories.interfaces.CountryRepository

class CountryRepositoryImpl : CountryRepository {

    private val fakeCountries = listOf(
        CountryDTO(
            countryID = 1,
            groupID = 1,
            name = "United States",
            stillExists = true,
            flagEmoji = "🇺🇸",
            hexColor = "#FF0000"
        ),
        CountryDTO(
            countryID = 2,
            groupID = 2,
            name = "Germany",
            stillExists = true,
            flagEmoji = "🇩🇪",
            hexColor = "#000000"
        )
    )

    override suspend fun getAll(): List<CountryDTO> {
        return fakeCountries
    }

    override suspend fun getById(id: Int): CountryDTO? {
        return fakeCountries.find { it.countryID == id }
    }
}