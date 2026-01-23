package app.msaproject.mainapp.routes

import app.msaproject.mainapp.repositories.implementations.QuizRepositoryImpl
import app.msaproject.mainapp.services.QuizService
import app.msaproject.mainapp.dtos.quiz.QuizDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.quizRoutes() {

    val quizService = QuizService(QuizRepositoryImpl())

    route("/quizzes") {

        // ===============================
        // GET /quizzes (Filtered, Sorted, Paginated)
        // ===============================
        get({
            description = "Retrieve quizzes with pagination, sorting and optional filters."

            request {

                queryParameter<Int>("page") {
                    description = "Page number starting from 1"
                    required = false
                }
                queryParameter<String>("sort") {
                    description = "Sort field: countryID, countryGroupID, quizTitle"
                    required = false
                }
                queryParameter<String>("order") {
                    description = "Sort order: asc or desc"
                    required = false
                }

                queryParameter<String>("countryID") {
                    description = "Filter by related country ID."
                    required = false
                }
                queryParameter<String>("countryGroupID") {
                    description = "Filter by related country group ID."
                    required = false
                }
                queryParameter<String>("quizTitle") {
                    description = "Filter by quiz title (partial match)."
                    required = false
                }
                queryParameter<String>("quizDescription") {
                    description = "Filter by quiz description (partial match)."
                    required = false
                }
            }

            response {
                code(HttpStatusCode.OK) {
                    description = "Paginated list of quizzes."
                    body<PaginatedResponseDTO<QuizDTO>>()
                }
            }
        }) {

            val countryID = call.request.queryParameters["countryID"]
            val countryGroupID = call.request.queryParameters["countryGroupID"]
            val quizTitle = call.request.queryParameters["quizTitle"]
            val quizDescription = call.request.queryParameters["quizDescription"]
            val sort = call.request.queryParameters["sort"]
            val order = call.request.queryParameters["order"]
            val page = call.request.queryParameters["page"]?.toIntOrNull()

            val response = quizService.getFiltered(
                countryID = countryID,
                countryGroupID = countryGroupID,
                quizTitle = quizTitle,
                quizDescription = quizDescription,
                sort = sort,
                order = order,
                page = page
            )

            call.respond(response)
        }

        // ===============================
        // GET /quizzes/{id}
        // ===============================
        get("{id}", {
            description = "Retrieve a specific quiz by its ID."

            request {
                pathParameter<String>("id") {
                    description = "The ID of the quiz."
                }
            }

            response {
                code(HttpStatusCode.OK) {
                    description = "Returns a single quiz entry."
                    body<QuizDTO>()
                }
            }
        }) {

            val id = call.parameters["id"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID format.")
                return@get
            }

            val quiz = quizService.getById(id)
            if (quiz == null) {
                call.respond(HttpStatusCode.NotFound, "Quiz not found")
            } else {
                call.respond(quiz)
            }
        }
    }
}
