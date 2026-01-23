package app.msaproject.mainapp.routes

import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullDTO
import app.msaproject.mainapp.dtos.countrygroup.CountryGroupFullPostDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.entities.GroupType
import app.msaproject.mainapp.repositories.implementations.CountryGroupRepositoryImpl
import app.msaproject.mainapp.services.CountryGroupService
import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.post
import io.github.smiley4.ktoropenapi.put
import io.github.smiley4.ktoropenapi.delete
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.request.receive

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
        post({
            description = "Create a new country group"
            request { body<CountryGroupFullPostDTO>() }
            response { code(HttpStatusCode.Created) { body<Int>() } }
        }) {
            val dto = call.receive<CountryGroupFullPostDTO>()
            val newId = service.create(dto)
            call.respond(HttpStatusCode.Created, newId)
        }

        put("{id}", {
            description = "Update an existing country group's details by its ID"
            request {
                pathParameter<Int>("id") { description = "The unique ID of the country group" }
                body<CountryGroupFullPostDTO>()
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Successfully updated. Returns the updated CountryGroupFullDTO"
                    body<CountryGroupFullDTO>()
                }
                code(HttpStatusCode.NotFound) { description = "Country group not found" }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)
            val dto = call.receive<CountryGroupFullPostDTO>()
            if (service.update(id, dto)) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

        delete("{id}", {
            description = "Permanently remove a country group from the database"
            request { pathParameter<Int>("id") { description = "The unique ID of the country group to delete" } }
            response {
                code(HttpStatusCode.OK) {
                    description = "Successfully deleted. Returns the deleted CountryGroupFullDTO metadata"
                    body<CountryGroupFullDTO>()
                }
                code(HttpStatusCode.NotFound) { description = "Country group not found" }
            }
        }) {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (service.delete(id)) call.respond(HttpStatusCode.NoContent)
            else call.respond(HttpStatusCode.NotFound)
        }
    }
}
