package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.dtos.country.CountryFullDTO
import app.msaproject.mainapp.repositories.interfaces.CountryRepository

class CountryRepositoryImpl : CountryRepository {

    private val fakeCountries = listOf(
        CountryFullDTO(
            countryID = 1,
            groupID = 1,
            name = "United States",
            stillExists = true,
            hexColor = "#FF0000"
        ),
        CountryFullDTO(
            countryID = 2,
            groupID = 2,
            name = "Germany",
            stillExists = true,
            hexColor = "#000000"
        )
    )

    override suspend fun getAll(): List<CountryFullDTO> {
        return fakeCountries
    }

    override suspend fun getById(id: Int): CountryFullDTO? {
        return fakeCountries.find { it.countryID == id }
    }
}