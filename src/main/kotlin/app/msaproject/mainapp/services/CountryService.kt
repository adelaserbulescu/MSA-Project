package app.msaproject.mainapp.services

import app.msaproject.mainapp.repositories.interfaces.CountryRepository

class CountryService(
    private val repository: CountryRepository
) {

    suspend fun getAll() = repository.getAll()

    suspend fun getById(countryID: Int) = repository.getByCountryID(countryID)

    suspend fun getFiltered(
        countryName: String?,
        groupID: Int?,
        stillExists: Boolean?,
        after: String?,
        before: String?,
        betweenStart: String?,
        betweenEnd: String?,
        sort: String?,
        order: String?,
        page: Int?
    ) = repository.getFiltered(
        countryName,
        groupID,
        stillExists,
        after,
        before,
        betweenStart,
        betweenEnd,
        sort,
        order,
        page
    )
}
