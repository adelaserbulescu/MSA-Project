package app.msaproject.mainapp.dtos_mocks.media

import app.msaproject.mainapp.dtos.media.MediaFullDTO
import app.msaproject.mainapp.entities.MediaType

object MockedMediaData {

    val mediaList = listOf(
        MediaFullDTO(
            mediaID = 1,
            countryID = 1,
            mediaType = MediaType.IMAGE,
            mediaPath = "/images/usa_flag.png"
        ),
        MediaFullDTO(
            mediaID = 2,
            countryID = 2,
            mediaType = MediaType.VIDEO,
            mediaPath = "https://www.youtube.com/watch?v=abc123"
        ),
        MediaFullDTO(
            mediaID = 3,
            countryID = 3,
            mediaType = MediaType.IMAGE,
            mediaPath = "/images/rome.png"
        ),
        MediaFullDTO(
            mediaID = 4,
            countryID = 2,
            mediaType = MediaType.VIDEO,
            mediaPath = "https://www.youtube.com/watch?v=xyz456"
        )
    )
}
