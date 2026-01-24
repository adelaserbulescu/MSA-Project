package app.msaproject.mainapp.routes

import app.msaproject.mainapp.repositories.implementations.CountryRepositoryImpl
import app.msaproject.mainapp.services.CountryService
import app.msaproject.mainapp.dtos.country.CountryFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.http.*

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
    }
}
