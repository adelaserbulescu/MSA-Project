package app.msaproject.mainapp.routes

import app.msaproject.mainapp.repositories.implementations.MediaRepositoryImpl
import app.msaproject.mainapp.services.MediaService
import app.msaproject.mainapp.dtos.media.MediaFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.entities.MediaType
import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.mediaRoutes() {

    val mediaService = MediaService(MediaRepositoryImpl())

    route("/media") {

        // GET ALL with filters, pagination, sorting
        get({
            description = "Get paginated & filtered media entries"
            request {
                // --- General Sorting & Pagination ---
                queryParameter<Int>("page") {
                    description = "Page number starting from 1"
                    required = false
                }
                queryParameter<String>("sort") {
                    description = "Sort field: mediaID or countryID"
                    required = false
                }
                queryParameter<String>("order") {
                    description = "Sort order: asc or desc"
                    required = false
                }

                // --- General Filters ---
                queryParameter<Int>("countryID") {
                    description = "Filter by country ID"
                    required = false
                }
                queryParameter<MediaType>("mediaType") {
                    description = "Filter by media type"
                    required = false
                }

            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns paginated media entries"
                    body<PaginatedResponseDTO<MediaFullDTO>>()
                }
            }
        }) {
            val countryID = call.request.queryParameters["countryID"]?.toIntOrNull()
            val mediaType = call.request.queryParameters["mediaType"]
            val page = call.request.queryParameters["page"]?.toIntOrNull()
            val sort = call.request.queryParameters["sort"]
            val order = call.request.queryParameters["order"]

            val response = mediaService.getFiltered(countryID, mediaType, page, sort, order)
            call.respond(response)
        }

        // GET media by ID
        get("{id}", {
            description = "Get a specific media entry by ID"
            request {
                pathParameter<String>("id") {
                    description = "The ID of the media entry"
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a media entry by ID"
                    body<MediaFullDTO>()
                }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }
            val media = mediaService.getById(id)
            if (media == null) {
                call.respond(HttpStatusCode.NotFound, "Media entry not found")
            } else {
                call.respond(media)
            }
        }
    }
}
