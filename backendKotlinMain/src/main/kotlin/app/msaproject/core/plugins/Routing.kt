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

        countryRoutes()
        mediaRoutes()
        countryGroupRoutes()
        htmlContentRoutes()
    }
}