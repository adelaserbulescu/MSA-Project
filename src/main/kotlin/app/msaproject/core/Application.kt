package app.msaproject.core

import app.msaproject.core.database.DatabaseFactory
import app.msaproject.core.plugins.configureOpenApi
import app.msaproject.core.plugins.configureRouting
import app.msaproject.core.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {

    configureRouting()
    configureOpenApi();
    configureSerialization()

    //DatabaseFactory.init();
    //DatabaseFactory.check_status()


}

