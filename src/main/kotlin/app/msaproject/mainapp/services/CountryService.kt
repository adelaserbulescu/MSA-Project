package app.msaproject.mainapp.services

import app.msaproject.mainapp.dtos.CountryDTO
import app.msaproject.mainapp.repositories.interfaces.CountryRepository

class CountryService(
    private val repository: CountryRepository
) {

    suspend fun getAllCountries(): List<CountryDTO> {
        return repository.getAll()
    }

    suspend fun getCountryById(id: Int): CountryDTO? {
        return repository.getById(id)
    }
}
