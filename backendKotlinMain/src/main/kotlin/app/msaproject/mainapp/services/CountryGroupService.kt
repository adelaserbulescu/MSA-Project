package app.msaproject.mainapp.services

import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullDTO
import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullPostDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.repositories.interfaces.CountryGroupRepository

class CountryGroupService(private val repository: CountryGroupRepository) {

    suspend fun getAll(): List<CountryGroupFullDTO> = repository.getAll()

    suspend fun getById(groupID: Int): CountryGroupFullDTO? = repository.getById(groupID)

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
    ): PaginatedResponseDTO<CountryGroupFullDTO> = repository.getFiltered(
        groupName, groupType, stillExists,
        dateStartedAfter, dateStartedBefore,
        dateEndedAfter, dateEndedBefore,
        page, sort, order
    )

    suspend fun create(dto: CountryGroupFullPostDTO) = repository.create(dto)

    suspend fun update(id: Int, dto: CountryGroupFullPostDTO) = repository.update(id, dto)

    suspend fun delete(id: Int) = repository.delete(id)
}
