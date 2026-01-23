package app.msaproject.mainapp.routes

import app.msaproject.mainapp.repositories.implementations.QuizQuestionRepositoryImpl
import app.msaproject.mainapp.services.QuizQuestionService
import app.msaproject.mainapp.dtos.quiz.QuizQuestionDTO
import io.github.smiley4.ktoropenapi.get
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Route.quizQuestionRoutes() {

    val questionService = QuizQuestionService(QuizQuestionRepositoryImpl())

    route("/quizzes/{quizId}/questions") {

        // ===============================
        // GET /quizzes/{quizId}/questions
        // ===============================
        get({
            description = "Retrieve all questions for a given quiz."

            request {
                pathParameter<String>("quizId") {
                    description = "The ID of the quiz."
                }
            }

            response {
                code(HttpStatusCode.OK) {
                    description = "List of questions for the quiz."
                    body<List<QuizQuestionDTO>>()
                }
            }
        }) {

            val quizId = call.parameters["quizId"]
            if (quizId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid quiz ID.")
                return@get
            }

            val questions = questionService.getById(quizId)
            call.respond(questions as Any)
        }
    }
}
