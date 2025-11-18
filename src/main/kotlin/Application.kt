import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.HttpStatusCode

import io.github.smiley4.ktoropenapi.OpenApi
import io.github.smiley4.ktoropenapi.config.OutputFormat
import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.openApi
import io.github.smiley4.ktorswaggerui.swaggerUI


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {

    install(ContentNegotiation) {
        json()
    }

    install(OpenApi) {
        info {
            title = "My API"
            version = "1.0.0"
            description = "API description"
        }
        outputFormat = OutputFormat.JSON
    }

    DatabaseFactory.init()
    DatabaseFactory.check_status()

    // Routing
    routing {
        route("api.json") {
            openApi()
        }
        route("swagger") {
            swaggerUI("/api.json")
        }

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
    }
}

