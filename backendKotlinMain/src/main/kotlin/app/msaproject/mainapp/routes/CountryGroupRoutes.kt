package app.msaproject.mainapp.routes

import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.entities.GroupType
import app.msaproject.mainapp.repositories.implementations.CountryGroupRepositoryImpl
import app.msaproject.mainapp.services.CountryGroupService
import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.countryGroupRoutes() {

    val service = CountryGroupService(CountryGroupRepositoryImpl())

    route("/country-groups") {

        // GET FILTERED + PAGINATED
        get({
            description = "Get filtered & paginated country groups"

            request {
                queryParameter<Int>("page") {
                    description = "Page number starting from 1"
                    required = false
                }
                queryParameter<String>("sort") {
                    description = "Sort field: groupID, groupName, dateStarted, dateEnded"
                    required = false
                }
                queryParameter<String>("order") {
                    description = "Sort order: asc or desc"
                    required = false
                }
                queryParameter<String>("groupName") {
                    description = "Filter by group name (partial match)"
                    required = false
                }
                queryParameter<GroupType>("groupType") {
                    description = "Filter by group type enum"
                    required = false
                }
                queryParameter<Boolean>("stillExists") {
                    description = "Filter active groups (true/false)"
                    required = false
                }
                queryParameter<String>("dateStartedAfter") {
                    description = "Filter groups starting after this date (yyyy-MM-dd)"
                    required = false
                }
                queryParameter<String>("dateStartedBefore") {
                    description = "Filter groups starting before this date (yyyy-MM-dd)"
                    required = false
                }
                queryParameter<String>("dateEndedAfter") {
                    description = "Filter groups ending after this date (yyyy-MM-dd)"
                    required = false
                }
                queryParameter<String>("dateEndedBefore") {
                    description = "Filter groups ending before this date (yyyy-MM-dd)"
                    required = false
                }
            }

            response {
                code(HttpStatusCode.OK) {
                    description = "Paginated list of filtered country groups"
                    body<PaginatedResponseDTO<CountryGroupFullDTO>>()
                }
            }
        }) {

            val page = call.request.queryParameters["page"]?.toIntOrNull()
            val sort = call.request.queryParameters["sort"]
            val order = call.request.queryParameters["order"]
            val groupName = call.request.queryParameters["groupName"]
            val groupType = call.request.queryParameters["groupType"]
            val stillExists = call.request.queryParameters["stillExists"]?.toBooleanStrictOrNull()
            val dateStartedAfter = call.request.queryParameters["dateStartedAfter"]
            val dateStartedBefore = call.request.queryParameters["dateStartedBefore"]
            val dateEndedAfter = call.request.queryParameters["dateEndedAfter"]
            val dateEndedBefore = call.request.queryParameters["dateEndedBefore"]

            val response = service.getFiltered(
                groupName, groupType, stillExists,
                dateStartedAfter, dateStartedBefore,
                dateEndedAfter, dateEndedBefore,
                page, sort, order
            )

            call.respond(response)
        }

        // GET BY ID
        get("{id}", {
            description = "Get a country group by ID"
            request {
                pathParameter<String>("id") {
                    description = "Group ID"
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a single CountryGroupFullDTO"
                    body<CountryGroupFullDTO>()
                }
            }
        }) {

            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }

            val group = service.getById(id)
            if (group == null)
                call.respond(HttpStatusCode.NotFound, "Group not found")
            else
                call.respond(group)
        }
    }
}
