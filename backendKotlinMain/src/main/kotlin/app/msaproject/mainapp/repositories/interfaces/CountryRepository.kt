package app.msaproject.mainapp.repositories.interfaces

import app.msaproject.mainapp.dtos.country.CountryFullDTO
import app.msaproject.mainapp.dtos.country.CountryFullPostDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO

interface CountryRepository {
    suspend fun getAll(): List<CountryFullDTO>

    suspend fun getByCountryID(countryID: Int): CountryFullDTO?

    suspend fun getFiltered(
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
    ): PaginatedResponseDTO<CountryFullDTO>

    suspend fun create(dto: CountryFullPostDTO): Int
    suspend fun update(id: Int, dto: CountryFullPostDTO): Boolean
    suspend fun delete(id: Int): Boolean
}