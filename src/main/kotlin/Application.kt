import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.HttpStatusCode

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {

    install(ContentNegotiation) {
        json()
    }

    DatabaseFactory.init()
    DatabaseFactory.check_status()

    // Routing
    routing {
        get("/") {
            call.respondText("Hello from Ktor backend!")
        }

        get("/hello") {
            call.respondText("Hello from Ktor backend! -Second Endpoint-")
        }
    }
}

