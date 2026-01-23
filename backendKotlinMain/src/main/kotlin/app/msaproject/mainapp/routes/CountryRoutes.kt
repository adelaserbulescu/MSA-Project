package app.msaproject.mainapp.routes

import app.msaproject.mainapp.repositories.implementations.CountryRepositoryImpl
import app.msaproject.mainapp.services.CountryService
import app.msaproject.mainapp.dtos.country.CountryFullDTO
import app.msaproject.mainapp.dtos.country.CountryFullPostDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.post
import io.github.smiley4.ktoropenapi.put
import io.github.smiley4.ktoropenapi.delete
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.request.receive

fun Route.countryRoutes() {

    val countryService = CountryService(CountryRepositoryImpl())

    route("/countries") {

        // ===============================
        // GET /countries (Filtered, Sorted, Paginated)
        // ===============================
        get({
            description = "Retrieve countries with pagination, sorting and optional filters."

            request {

                // ================= GENERAL SORTING & PAGINATION =================
                queryParameter<Int>("page") {
                    description = "Page number starting from 1"
                    required = false
                }
                queryParameter<String>("sort") {
                    description = "Sort field: countryName, dateStarted, dateEnded"
                    required = false
                }
                queryParameter<String>("order") {
                    description = "Sort order: asc or desc"
                    required = false
                }

                // ================= GENERAL FILTERS =================
                queryParameter<String>("countryName") {
                    description = "Filter by country name (full or partial match)."
                    required = false
                }
                queryParameter<Int>("groupID") {
                    description = "Filter by Country Group ID."
                    required = false
                }
                queryParameter<Boolean>("stillExists") {
                    description = "Filter by existence. True = still exists; False = historical."
                    required = false
                }

                // ================= DATE FILTERS =================
                queryParameter<String>("after") {
                    description = "Return countries formed AFTER this date. Format: YYYY-MM-DD."
                    required = false
                }
                queryParameter<String>("before") {
                    description = "Return countries formed BEFORE this date. Format: YYYY-MM-DD."
                    required = false
                }
                queryParameter<String>("betweenStart") {
                    description = "Start of a date range filter (dateStarted >= this). Format: YYYY-MM-DD."
                    required = false
                }
                queryParameter<String>("betweenEnd") {
                    description = "End of a date range filter (dateEnded <= this). Format: YYYY-MM-DD."
                    required = false
                }
            }

            response {
                code(HttpStatusCode.OK) {
                    description = "Paginated list of filtered countries."
                    body<PaginatedResponseDTO<CountryFullDTO>>()
                }
            }
        }) {

            // ================= Read Query Parameters =================
            val countryName = call.request.queryParameters["countryName"]
            val groupID = call.request.queryParameters["groupID"]?.toIntOrNull()
            val stillExists = call.request.queryParameters["stillExists"]?.toBooleanStrictOrNull()
            val after = call.request.queryParameters["after"]
            val before = call.request.queryParameters["before"]
            val betweenStart = call.request.queryParameters["betweenStart"]
            val betweenEnd = call.request.queryParameters["betweenEnd"]
            val sort = call.request.queryParameters["sort"]
            val order = call.request.queryParameters["order"]
            val page = call.request.queryParameters["page"]?.toIntOrNull()

            // ================= Execute Service Call =================
            val response = countryService.getFiltered(
                countryName = countryName,
                groupID = groupID,
                stillExists = stillExists,
                after = after,
                before = before,
                betweenStart = betweenStart,
                betweenEnd = betweenEnd,
                sort = sort,
                order = order,
                page = page
            )

            call.respond(response)
        }

        // ===============================
        // GET /countries/{id}
        // ===============================
        get("{id}", {
            description = "Retrieve a specific country by its ID."

            request {
                pathParameter<String>("id") {
                    description = "The ID of the country."
                }
            }

            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a single country entry."
                    body<CountryFullDTO>()
                }
            }
        }) {

            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID format.")
                return@get
            }

            val country = countryService.getById(id)
            if (country == null) {
                call.respond(HttpStatusCode.NotFound, "Country not found")
            } else {
                call.respond(country)
            }
        }
        post({
            description = "Create a new country"
            request { body<CountryFullPostDTO>() }
            response { code(HttpStatusCode.Created) { body<Int>() } }
        }) {
            val dto = call.receive<CountryFullPostDTO>()
            val newId = countryService.create(dto)
            call.respond(HttpStatusCode.Created, newId)
        }

        put("{id}", {
            description = "Update an existing country's information (name, dates, existence status)"
            request {
                pathParameter<Int>("id") { description = "The unique ID of the country" }
                body<CountryFullPostDTO>()
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Successfully updated. Returns the updated CountryFullDTO"
                    body<CountryFullDTO>()
                }
                code(HttpStatusCode.NotFound) { description = "Country not found" }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)
            val dto = call.receive<CountryFullPostDTO>()
            if (countryService.update(id, dto)) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

        delete("{id}", {
            description = "Remove a country record. Warning: This may fail if linked to Media or HTML content"
            request { pathParameter<Int>("id") { description = "The unique ID of the country to delete" } }
            response {
                code(HttpStatusCode.OK) {
                    description = "Successfully deleted. Returns the deleted CountryFullDTO metadata"
                    body<CountryFullDTO>()
                }
                code(HttpStatusCode.NotFound) { description = "Country not found" }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (countryService.delete(id)) call.respond(HttpStatusCode.NoContent)
            else call.respond(HttpStatusCode.NotFound)
        }
    }
}
