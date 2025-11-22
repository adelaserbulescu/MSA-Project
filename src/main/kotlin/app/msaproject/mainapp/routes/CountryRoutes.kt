package app.msaproject.mainapp.routes

import app.msaproject.mainapp.repositories.implementations.CountryRepositoryImpl
import app.msaproject.mainapp.services.CountryService
import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.countryRoutes() {
    val countryService = CountryService(CountryRepositoryImpl())

    route("/countries") {

        get({
            description = "Get a list of all countries"
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns all countries"
                    body<String>()
                }
            }
        }) {
            call.respond(countryService.getAllCountries())
        }

        get("{id}", {
            description = "Get a specific country by its ID"
            request {
                pathParameter<String>("id") {
                    description = "The ID of the country"
                }
            }
            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a country by ID"
                    body<String>()
                }
            }
        }) {
            val id = call.parameters["id"]!!
            call.respondText("Returning country with ID $id (placeholder)")
        }
    }
}
