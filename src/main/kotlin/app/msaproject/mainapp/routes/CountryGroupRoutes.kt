package app.msaproject.mainapp.routes

import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.countryGroupRoutes() {

    route("/country-groups") {

        get({
            description = "Get all country groups"
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns all country groups"
                    body<String>()
                }
            }
        }) {
            call.respondText("Returning ALL country groups (placeholder)")
        }

        get("{id}", {
            description = "Get a specific country group by ID"
            request {
                pathParameter<String>("id") {
                    description = "ID of the country group"
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a country group by ID"
                    body<String>()
                }
            }
        }) {
            val id = call.parameters["id"]
            call.respondText("Returning country group with ID $id (placeholder)")
        }
    }
}
