package app.msaproject.mainapp.routes

import app.msaproject.mainapp.repositories.implementations.MediaRepositoryImpl
import app.msaproject.mainapp.services.MediaService
import app.msaproject.mainapp.dtos.media.MediaFullDTO
import app.msaproject.mainapp.dtos.media.MediaFullPostDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.entities.MediaType
import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.post
import io.github.smiley4.ktoropenapi.put
import io.github.smiley4.ktoropenapi.delete
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.request.receive

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
        post({
            description = "Create a new media entry"
            request { body<MediaFullPostDTO>() }
            response { code(HttpStatusCode.Created) { body<Int>() } }
        }) {
            val dto = call.receive<MediaFullPostDTO>()
            val newId = mediaService.create(dto)
            call.respond(HttpStatusCode.Created, newId)
        }

        put("{id}", {
            description = "Update a media entry's path or type (Image, Video, etc.)"
            request {
                pathParameter<Int>("id") { description = "The unique ID of the media entry" }
                body<MediaFullPostDTO>()
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Successfully updated. Returns the updated MediaFullDTO"
                    body<MediaFullDTO>()
                }
                code(HttpStatusCode.NotFound) { description = "Media entry not found" }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)
            val dto = call.receive<MediaFullPostDTO>()
            if (mediaService.update(id, dto)) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

        delete("{id}", {
            description = "Remove a media resource reference from the system"
            request { pathParameter<Int>("id") { description = "The unique ID of the media entry to delete" } }
            response {
                code(HttpStatusCode.OK) {
                    description = "Successfully deleted. Returns the deleted MediaFullDTO metadata"
                    body<MediaFullDTO>()
                }
                code(HttpStatusCode.NotFound) { description = "Media entry not found" }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (mediaService.delete(id)) call.respond(HttpStatusCode.NoContent)
            else call.respond(HttpStatusCode.NotFound)
        }
    }
}
