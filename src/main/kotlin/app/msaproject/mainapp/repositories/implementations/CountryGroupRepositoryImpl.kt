package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.dtos_mocks.countrygroup.MockedCountryGroupData
import app.msaproject.mainapp.repositories.interfaces.CountryGroupRepository
import app.msaproject.mainapp.utils.PaginationUtils

class CountryGroupRepositoryImpl : CountryGroupRepository {

    private val groups = MockedCountryGroupData.groupList

    override suspend fun getAll(): List<CountryGroupFullDTO> = groups

    override suspend fun getById(groupID: Int): CountryGroupFullDTO? =
        groups.find { it.groupID == groupID }

    override suspend fun getFiltered(
        groupName: String?,
        groupType: String?,
        stillExists: Boolean?,
        dateStartedAfter: String?,
        dateStartedBefore: String?,
        dateEndedAfter: String?,
        dateEndedBefore: String?,
        page: Int?,
        sort: String?,
        order: String?
    ): PaginatedResponseDTO<CountryGroupFullDTO> {

        var result = groups.filter { g ->
            (groupName == null || g.groupName.contains(groupName, ignoreCase = true)) &&
                    (groupType == null || g.groupType?.name.equals(groupType, ignoreCase = true)) &&
                    (stillExists == null || g.stillExists == stillExists) &&
                    (dateStartedAfter == null || (g.dateStarted?.compareTo(dateStartedAfter) ?: 0) >= 0) &&
                    (dateStartedBefore == null || (g.dateStarted?.compareTo(dateStartedBefore) ?: 0) <= 0) &&
                    (dateEndedAfter == null || (g.dateEnded?.compareTo(dateEndedAfter) ?: 0) >= 0) &&
                    (dateEndedBefore == null || (g.dateEnded?.compareTo(dateEndedBefore) ?: 0) <= 0)
        }

        // Sorting
        if (sort != null) {
            result = when (sort) {
                "groupID" -> result.sortedBy { it.groupID }
                "groupName" -> result.sortedBy { it.groupName }
                "dateStarted" -> result.sortedBy { it.dateStarted }
                "dateEnded" -> result.sortedBy { it.dateEnded }
                else -> result
            }
            if (order == "desc") result = result.reversed()
        }

        // Pagination
        return PaginationUtils.paginate(
            list = result,
            page = page,
            pageSize = PaginationConfig.countryGroupPageLimit
        )
    }
}
