package app.msaproject.mainapp.routes

import app.msaproject.mainapp.repositories.implementations.HtmlContentRepositoryImpl
import app.msaproject.mainapp.services.HtmlContentService
import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullDTO
import app.msaproject.mainapp.dtos.htmlcontent.HtmlContentFullPostDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.entities.HtmlContentType
import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.post
import io.github.smiley4.ktoropenapi.put
import io.github.smiley4.ktoropenapi.delete
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.request.receive

fun Route.htmlContentRoutes() {

    val service = HtmlContentService(HtmlContentRepositoryImpl())

    route("/html-content") {

        // GET FILTERED + PAGINATED
        get({
            description = "Get filtered & paginated HTML content entries"
            request {

                // --- General Pagination & Sorting ---
                queryParameter<Int>("page") {
                    description = "Page number starting from 1"
                    required = false
                }
                queryParameter<String>("sort") {
                    description = "Sort field: htmlContentID, countryID, dateAdded"
                    required = false
                }
                queryParameter<String>("order") {
                    description = "Sort direction: asc or desc"
                    required = false
                }

                // --- Filters ---
                queryParameter<Int>("countryID") {
                    description = "Filter by country ID"
                    required = false
                }
                queryParameter<HtmlContentType>("contentType") {
                    description = "Filter by content type enum"
                    required = false
                }
                queryParameter<Int>("version") {
                    description = "Filter by specific version"
                    required = false
                }
                queryParameter<Int>("pageIndex") {
                    description = "Page index where the HTML block belongs"
                    required = false
                }
                queryParameter<String>("pageSource") {
                    description = "Source page name"
                    required = false
                }
                queryParameter<String>("after") {
                    description = "Filter: dateAdded after this value (yyyy-MM-dd)"
                    required = false
                }
                queryParameter<String>("before") {
                    description = "Filter: dateAdded before this value (yyyy-MM-dd)"
                    required = false
                }
                queryParameter<Boolean>("latestOnly") {
                    description = "If true, return only latest version per unique content grouping"
                    required = false
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns paginated HTML content entries"
                    body<PaginatedResponseDTO<HtmlContentFullDTO>>()
                }
            }
        }) {
            val countryID = call.request.queryParameters["countryID"]?.toIntOrNull()
            val contentType = call.request.queryParameters["contentType"]
            val version = call.request.queryParameters["version"]?.toIntOrNull()
            val pageIndex = call.request.queryParameters["pageIndex"]?.toIntOrNull()
            val pageSource = call.request.queryParameters["pageSource"]
            val after = call.request.queryParameters["after"]
            val before = call.request.queryParameters["before"]
            val latestOnly = call.request.queryParameters["latestOnly"]?.toBooleanStrictOrNull() ?: false
            val page = call.request.queryParameters["page"]?.toIntOrNull()
            val sort = call.request.queryParameters["sort"]
            val order = call.request.queryParameters["order"]

            val response = service.getFiltered(
                countryID, contentType, version, pageIndex, pageSource,
                after, before, latestOnly, page, sort, order
            )

            call.respond(response)
        }

        // GET BY ID
        get("{id}", {
            description = "Get one HTML content entry by ID"
            request {
                pathParameter<String>("id") {
                    description = "HTML content ID"
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a single HTML content entry"
                    body<HtmlContentFullDTO>()
                }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }

            val obj = service.getById(id)
            if (obj == null) {
                call.respond(HttpStatusCode.NotFound, "HTML content not found")
            } else call.respond(obj)
        }
        post({
            description = "Create a new HTML content entry"
            request { body<HtmlContentFullPostDTO>() }
            response { code(HttpStatusCode.Created) { body<Int>() } }
        }) {
            val dto = call.receive<HtmlContentFullPostDTO>()
            val newId = service.create(dto)
            call.respond(HttpStatusCode.Created, newId)
        }

        put("{id}", {
            description = "Update specific HTML content block, including versioning and page index"
            request {
                pathParameter<Int>("id") { description = "The unique ID of the HTML content entry" }
                body<HtmlContentFullPostDTO>()
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Successfully updated. Returns the updated HtmlContentFullDTO"
                    body<HtmlContentFullDTO>()
                }
                code(HttpStatusCode.NotFound) { description = "HTML content entry not found" }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)
            val dto = call.receive<HtmlContentFullPostDTO>()
            if (service.update(id, dto)) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

        delete("{id}", {
            description = "Delete a specific HTML content version/entry"
            request { pathParameter<Int>("id") { description = "The unique ID of the HTML content to delete" } }
            response {
                code(HttpStatusCode.OK) {
                    description = "Successfully deleted. Returns the deleted HtmlContentFullDTO metadata"
                    body<HtmlContentFullDTO>()
                }
                code(HttpStatusCode.NotFound) { description = "HTML content entry not found" }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (service.delete(id)) call.respond(HttpStatusCode.NoContent)
            else call.respond(HttpStatusCode.NotFound)
        }
    }
}
