package app.msaproject.mainapp.routes

import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.countryRoadMapRoutes() {

    route("/country-roadmaps") {

        get({
            description = "Get all country roadmaps"
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns all country roadmaps"
                    body<String>()
                }
            }
        }) {
            call.respondText("Returning ALL country roadmaps (placeholder)")
        }

        get("{id}", {
            description = "Get a specific country roadmap by ID"
            request {
                pathParameter<String>("id") {
                    description = "ID of the country roadmap"
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a country roadmap by ID"
                    body<String>()
                }
            }
        }) {
            val id = call.parameters["id"]
            call.respondText("Returning country roadmap with ID $id (placeholder)")
        }
    }
}
