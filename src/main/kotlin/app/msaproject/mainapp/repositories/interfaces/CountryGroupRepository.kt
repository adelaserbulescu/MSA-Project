package app.msaproject.mainapp.repositories.interfaces

import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO

interface CountryGroupRepository {

    suspend fun getAll(): List<CountryGroupFullDTO>

    suspend fun getById(groupID: Int): CountryGroupFullDTO?

    suspend fun getFiltered(
        groupName: String? = null,
        groupType: String? = null,
        stillExists: Boolean? = null,
        dateStartedAfter: String? = null,
        dateStartedBefore: String? = null,
        dateEndedAfter: String? = null,
        dateEndedBefore: String? = null,
        page: Int? = null,
        sort: String? = null,
        order: String? = null
    ): PaginatedResponseDTO<CountryGroupFullDTO>
}
