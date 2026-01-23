package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.media.MediaFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.dtos_formatters.toMediaFullDTO
import app.msaproject.mainapp.entities.MediaEntity
import app.msaproject.mainapp.entities.MediaType
import app.msaproject.mainapp.repositories.interfaces.MediaRepository
import app.msaproject.mainapp.utils.PaginationUtils
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class MediaRepositoryImpl : MediaRepository {

    override suspend fun getAll(): List<MediaFullDTO> =
        transaction {
            MediaEntity.selectAll().map { it.toMediaFullDTO() }
        }

    override suspend fun getById(mediaID: Int): MediaFullDTO? =
        transaction {
            MediaEntity
                .select { MediaEntity.mediaID eq mediaID }
                .map { it.toMediaFullDTO() }
                .singleOrNull()
        }

    override suspend fun getFiltered(
        countryID: Int?,
        mediaType: String?,
        page: Int?,
        sort: String?,
        order: String?
    ): PaginatedResponseDTO<MediaFullDTO> {

        return transaction {

            val query = MediaEntity.selectAll()

            if (countryID != null)
                query.andWhere { MediaEntity.countryID eq countryID }

            val typeEnum = mediaType?.let { MediaType.valueOf(it.uppercase()) }
            if (typeEnum != null)
                query.andWhere { MediaEntity.mediaType eq typeEnum }


            val sorted = when (sort) {
                "mediaID"   -> query.orderBy(MediaEntity.mediaID to sortOrder(order))
                "countryID" -> query.orderBy(MediaEntity.countryID to sortOrder(order))
                else -> query
            }

            val list = sorted.map { it.toMediaFullDTO() }

            PaginationUtils.paginate(
                list = list,
                page = page,
                pageSize = PaginationConfig.mediaPageLimit
            )
        }
    }

    private fun sortOrder(order: String?) =
        if (order == "desc") SortOrder.DESC else SortOrder.ASC
}
