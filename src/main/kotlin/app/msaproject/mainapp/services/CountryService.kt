package app.msaproject.mainapp.services

import app.msaproject.mainapp.dtos.country.CountryFullDTO
import app.msaproject.mainapp.repositories.interfaces.CountryRepository

class CountryService(
    private val repository: CountryRepository
) {

    suspend fun getAllCountries(): List<CountryFullDTO> {
        return repository.getAll()
    }

    suspend fun getCountryById(id: Int): CountryFullDTO? {
        return repository.getById(id)
    }
}
