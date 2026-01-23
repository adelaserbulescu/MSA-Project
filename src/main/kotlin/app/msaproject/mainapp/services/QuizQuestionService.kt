package app.msaproject.mainapp.services

import app.msaproject.mainapp.repositories.interfaces.QuizQuestionRepository

class QuizQuestionService(
    private val repository: QuizQuestionRepository
) {

    suspend fun getAll() = repository.getAll()

    suspend fun getById(quizID: String) = repository.getById(quizID)

    suspend fun getFiltered(
        questionTitle: String,
        questionBody: String,
        sort: String? = null,
        order: String? = null,
        page: Int?
    ) = repository.getFiltered(
        questionTitle,
        questionBody,
        questionBody,
        sort,
        order,
        page
    )
}
