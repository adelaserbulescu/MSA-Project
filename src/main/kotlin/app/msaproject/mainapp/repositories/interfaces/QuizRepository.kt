package app.msaproject.mainapp.repositories.interfaces

import app.msaproject.mainapp.dtos.quiz.QuizDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO

interface QuizRepository {
    suspend fun getAll(): List<QuizDTO>

    suspend fun getById(id: String): QuizDTO?

    suspend fun getFiltered(
        countryID: String? = null,
        countryGroupID: String? = null,
        quizTitle: String? = null,
        quizDescription: String? = null,
        sort: String? = null,
        order: String? = null,
        page: Int?
    ): PaginatedResponseDTO<QuizDTO>
}