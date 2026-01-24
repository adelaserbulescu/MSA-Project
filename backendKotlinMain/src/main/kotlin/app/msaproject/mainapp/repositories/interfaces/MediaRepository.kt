package app.msaproject.mainapp.repositories.interfaces

import app.msaproject.mainapp.dtos.media.MediaFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO

interface MediaRepository {

    suspend fun getAll(): List<MediaFullDTO>

    suspend fun getById(mediaID: Int): MediaFullDTO?

    suspend fun getFiltered(
        countryID: Int? = null,
        mediaType: String? = null,
        page: Int? = null,
        sort: String? = null,
        order: String? = null
    ): PaginatedResponseDTO<MediaFullDTO>
}
