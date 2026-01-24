package app.msaproject.mainapp.services

import app.msaproject.mainapp.dtos.media.MediaFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.repositories.interfaces.MediaRepository

class MediaService(private val repository: MediaRepository) {

    suspend fun getAll(): List<MediaFullDTO> = repository.getAll()

    suspend fun getById(mediaID: Int): MediaFullDTO? = repository.getById(mediaID)

    suspend fun getFiltered(
        countryID: Int?,
        mediaType: String?,
        page: Int?,
        sort: String?,
        order: String?
    ): PaginatedResponseDTO<MediaFullDTO> =
        repository.getFiltered(countryID, mediaType, page, sort, order)
}
