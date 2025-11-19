package app.msaproject.core.plugins

import io.ktor.server.application.*
import io.github.smiley4.ktoropenapi.OpenApi
import io.github.smiley4.ktoropenapi.config.OutputFormat

fun Application.configureOpenApi() {
    install(OpenApi) {
        info {
            title = "My API"
            version = "1.0.0"
            description = "API description"
        }
        outputFormat = OutputFormat.JSON
    }
}
