package app.msaproject.mainapp.services

import app.msaproject.mainapp.repositories.interfaces.QuizRepository

class QuizService(
    private val repository: QuizRepository
) {

    suspend fun getAll() = repository.getAll()

    suspend fun getById(quizID: String) = repository.getById(quizID)

    suspend fun getFiltered(
        countryID: String?,
        countryGroupID: String?,
        quizTitle: String?,
        quizDescription: String?,
        sort: String?,
        order: String?,
        page: Int?
    ) = repository.getFiltered(
        countryID,
        countryGroupID,
        quizTitle,
        quizDescription,
        sort,
        order,
        page
    )
}
