package app.msaproject.mainapp.routes

import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.mediaRoutes() {

    route("/media") {

        get({
            description = "Get a list of all media entries"
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns all media entries"
                    body<String>()
                }
            }
        }) {
            call.respondText("Returning ALL media entries (placeholder)")
        }

        get("{id}", {
            description = "Get a specific media entry by its ID"
            request {
                pathParameter<String>("id") {
                    description = "The ID of the media entry"
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a media entry by ID"
                    body<String>()
                }
            }
        }) {
            val id = call.parameters["id"]
            call.respondText("Returning media entry with ID $id (placeholder)")
        }
    }
}
