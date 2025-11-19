package app.msaproject.core.plugins

import app.msaproject.mainapp.routes.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.openApi
import io.github.smiley4.ktorswaggerui.swaggerUI

fun Application.configureRouting() {

    routing {

        // OpenAPI JSON endpoint
        route("api.json") {
            openApi()
        }

        // SwaggerUI
        route("swagger") {
            swaggerUI("/api.json")
        }


        // Examples
        get("/hello-world", {
            description = "A simple hello world endpoint"
            request {
                queryParameter<String>("name") {
                    description = "The name to greet"
                    required = false
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a greeting message"
                    body<String>()
                }
            }
        }) {
            val name = call.request.queryParameters["name"] ?: "World"
            call.respondText("Hello $name!")
        }

        get("/") {
            call.respondText("Hello from Ktor backend!")
        }

        get("/hello") {
            call.respondText("Hello from Ktor backend! -Second Endpoint-")
        }

        countryRoutes()
        mediaRoutes()
        countryGroupRoutes()
        countryRoadMapRoutes()
    }
}