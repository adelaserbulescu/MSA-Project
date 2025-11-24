package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.country.CountryFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.dtos_mocks.country.MockedCountryData
import app.msaproject.mainapp.repositories.interfaces.CountryRepository
import app.msaproject.mainapp.utils.PaginationUtils

class CountryRepositoryImpl : CountryRepository {

    private val countries = MockedCountryData.countries

    override suspend fun getAll(): List<CountryFullDTO> {
        return countries
    }

    override suspend fun getByCountryID(countryID: Int): CountryFullDTO? {
        return countries.find { it.countryID == countryID }
    }

    override suspend fun getFiltered(
        countryName: String?,
        groupID: Int?,
        stillExists: Boolean?,
        afterDate: String?,
        beforeDate: String?,
        betweenStart: String?,
        betweenEnd: String?,
        sort: String?,
        order: String?,
        page: Int?
    ): PaginatedResponseDTO<CountryFullDTO> {

        val fixedPageSize = PaginationConfig.countryPageLimit

        var result = countries.filter { c ->

            val nameMatches = countryName?.let { c.countryName.contains(it, ignoreCase = true) } ?: true

            val groupMatches = groupID?.let { c.groupID == it } ?: true

            val stillExistsMatches = stillExists?.let { c.stillExists == it } ?: true

            val afterMatches = afterDate?.let {
                c.dateStarted?.let { ds -> ds >= it } ?: false
            } ?: true

            val beforeMatches = beforeDate?.let {
                c.dateStarted?.let { ds -> ds <= it } ?: false
            } ?: true

            val betweenMatches = if (betweenStart != null && betweenEnd != null) {
                c.dateStarted?.let { ds ->
                    ds >= betweenStart && ds <= betweenEnd
                } ?: false
            } else true

            nameMatches &&
                    groupMatches &&
                    stillExistsMatches &&
                    afterMatches &&
                    beforeMatches &&
                    betweenMatches
        }

        // Sorting
        if (sort != null) {
            result = when (sort) {
                "countryName" -> result.sortedBy { it.countryName }
                "dateStarted" -> result.sortedBy { it.dateStarted }
                "dateEnded"   -> result.sortedBy { it.dateEnded }
                "groupID"     -> result.sortedBy { it.groupID }
                else -> result // ignore invalid sort parameter
            }

            if (order == "desc") {
                result = result.reversed()
            }
        }

        // Pagination
        return PaginationUtils.paginate(
            list = result,
            page = page,
            pageSize = PaginationConfig.countryPageLimit,
        )

    }
}
