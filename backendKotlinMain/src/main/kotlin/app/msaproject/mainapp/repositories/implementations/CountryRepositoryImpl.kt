package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.country.CountryFullDTO
import app.msaproject.mainapp.dtos.country.CountryFullPostDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.dtos_formatters.toCountryFullDTO
import app.msaproject.mainapp.entities.CountryEntity
import app.msaproject.mainapp.repositories.interfaces.CountryRepository
import app.msaproject.mainapp.utils.PaginationUtils
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

class CountryRepositoryImpl : CountryRepository {

    override suspend fun getAll(): List<CountryFullDTO> =
        transaction {
            CountryEntity.selectAll().map { it.toCountryFullDTO() }
        }

    override suspend fun getByCountryID(countryID: Int): CountryFullDTO? =
        transaction {
            CountryEntity
                .select { CountryEntity.countryID eq countryID }
                .map { it.toCountryFullDTO() }
                .singleOrNull()
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

        return transaction {

            val query = CountryEntity.selectAll()

            if (countryName != null)
                query.andWhere { CountryEntity.countryName like "%$countryName%" }

            if (groupID != null)
                query.andWhere { CountryEntity.groupID eq groupID }

            if (stillExists != null)
                query.andWhere { CountryEntity.stillExists eq stillExists }

            val after = afterDate?.let { LocalDate.parse(it) }
            if (after != null)
                query.andWhere { CountryEntity.dateStarted greaterEq after }

            val before = beforeDate?.let { LocalDate.parse(it) }
            if (before != null)
                query.andWhere { CountryEntity.dateStarted lessEq before }

            val betweenStartDate = betweenStart?.let { LocalDate.parse(it) }
            val betweenEndDate = betweenEnd?.let { LocalDate.parse(it) }
            if (betweenStartDate != null && betweenEndDate != null)
                query.andWhere {
                    (CountryEntity.dateStarted greaterEq betweenStartDate) and
                            (CountryEntity.dateStarted lessEq betweenEndDate)
                }


            val sorted = when (sort) {
                "countryName" -> query.orderBy(CountryEntity.countryName to sortOrder(order))
                "dateStarted" -> query.orderBy(CountryEntity.dateStarted to sortOrder(order))
                "dateEnded"   -> query.orderBy(CountryEntity.dateEnded to sortOrder(order))
                "groupID"     -> query.orderBy(CountryEntity.groupID to sortOrder(order))
                else -> query
            }

            val list = sorted.map { it.toCountryFullDTO() }

            PaginationUtils.paginate(
                list = list,
                page = page,
                pageSize = PaginationConfig.countryPageLimit
            )
        }
    }

    private fun sortOrder(order: String?) =
        if (order == "desc") SortOrder.DESC else SortOrder.ASC

    override suspend fun create(dto: CountryFullPostDTO): Int = transaction {
        CountryEntity.insert {
            it[groupID] = dto.groupID
            it[countryName] = dto.countryName
            it[dateStarted] = dto.dateStarted?.let { d -> LocalDate.parse(d) }
            it[dateEnded] = dto.dateEnded?.let { d -> LocalDate.parse(d) }
            it[stillExists] = dto.stillExists
            it[flagImagePath] = dto.flagImagePath
            it[hexColor] = dto.hexColor
        }[CountryEntity.countryID]
    }

    override suspend fun update(id: Int, dto: CountryFullPostDTO): Boolean = transaction {
        CountryEntity.update({ CountryEntity.countryID eq id }) {
            it[groupID] = dto.groupID
            it[countryName] = dto.countryName
            it[dateStarted] = dto.dateStarted?.let { d -> LocalDate.parse(d) }
            it[dateEnded] = dto.dateEnded?.let { d -> LocalDate.parse(d) }
            it[stillExists] = dto.stillExists
            it[flagImagePath] = dto.flagImagePath
            it[hexColor] = dto.hexColor
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = transaction {
        CountryEntity.deleteWhere { CountryEntity.countryID eq id } > 0
    }
}
