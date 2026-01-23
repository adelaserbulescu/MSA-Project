package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.dtos_formatters.toCountryGroupFullDTO
import app.msaproject.mainapp.entities.CountryGroupEntity
import app.msaproject.mainapp.entities.GroupType
import app.msaproject.mainapp.repositories.interfaces.CountryGroupRepository
import app.msaproject.mainapp.utils.PaginationUtils
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

class CountryGroupRepositoryImpl : CountryGroupRepository {

    override suspend fun getAll(): List<CountryGroupFullDTO> =
        transaction {
            CountryGroupEntity
                .selectAll()
                .map { it.toCountryGroupFullDTO() }
        }

    override suspend fun getById(groupID: Int): CountryGroupFullDTO? =
        transaction {
            CountryGroupEntity
                .select { CountryGroupEntity.groupID eq groupID }
                .map { it.toCountryGroupFullDTO() }
                .singleOrNull()
        }

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

        return transaction {

            val query = CountryGroupEntity.selectAll()

            if (groupName != null)
                query.andWhere { CountryGroupEntity.groupName like "%$groupName%" }

            if (stillExists != null)
                query.andWhere { CountryGroupEntity.stillExists eq stillExists }

            val typeEnum = groupType?.let { GroupType.valueOf(it.uppercase()) }
            if (typeEnum != null)
                query.andWhere { CountryGroupEntity.groupType eq typeEnum }

            val startAfter = dateStartedAfter?.let { LocalDate.parse(it) }
            if (startAfter != null)
                query.andWhere { CountryGroupEntity.dateStarted greaterEq startAfter }

            val startBefore = dateStartedBefore?.let { LocalDate.parse(it) }
            if (startBefore != null)
                query.andWhere { CountryGroupEntity.dateStarted lessEq startBefore }

            val endAfter = dateEndedAfter?.let { LocalDate.parse(it) }
            if (endAfter != null)
                query.andWhere { CountryGroupEntity.dateEnded greaterEq endAfter }

            val endBefore = dateEndedBefore?.let { LocalDate.parse(it) }
            if (endBefore != null)
                query.andWhere { CountryGroupEntity.dateEnded lessEq endBefore }


            val sorted = when (sort) {
                "groupID"     -> query.orderBy(CountryGroupEntity.groupID to sortOrder(order))
                "groupName"   -> query.orderBy(CountryGroupEntity.groupName to sortOrder(order))
                "dateStarted" -> query.orderBy(CountryGroupEntity.dateStarted to sortOrder(order))
                "dateEnded"   -> query.orderBy(CountryGroupEntity.dateEnded to sortOrder(order))
                else -> query
            }

            val list = sorted.map { it.toCountryGroupFullDTO() }

            PaginationUtils.paginate(
                list = list,
                page = page,
                pageSize = PaginationConfig.countryGroupPageLimit
            )
        }
    }

    private fun sortOrder(order: String?) =
        if (order == "desc") SortOrder.DESC else SortOrder.ASC
}
