package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.dtos.media.MediaFullDTO
import app.msaproject.mainapp.dtos_mocks.media.MockedMediaData
import app.msaproject.mainapp.repositories.interfaces.MediaRepository
import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.utils.PaginationUtils

class MediaRepositoryImpl : MediaRepository {

    private val mediaList = MockedMediaData.mediaList

    override suspend fun getAll(): List<MediaFullDTO> = mediaList

    override suspend fun getById(mediaID: Int): MediaFullDTO? =
        mediaList.find { it.mediaID == mediaID }

    override suspend fun getFiltered(
        countryID: Int?,
        mediaType: String?,
        page: Int?,
        sort: String?,
        order: String?
    ): PaginatedResponseDTO<MediaFullDTO> {

        var result = mediaList.filter { m ->
            val countryMatches = countryID?.let { m.countryID == it } ?: true
            val typeMatches = mediaType?.let { m.mediaType?.name.equals(it, ignoreCase = true) } ?: true
            countryMatches && typeMatches
        }

        // Sorting
        if (sort != null) {
            result = when (sort) {
                "mediaID" -> result.sortedBy { it.mediaID }
                "countryID" -> result.sortedBy { it.countryID }
                else -> result
            }
            if (order == "desc") result = result.reversed()
        }

        // Pagination
        return PaginationUtils.paginate(
            list = result,
            page = page,
            pageSize = PaginationConfig.mediaPageLimit
        )

    }
}
