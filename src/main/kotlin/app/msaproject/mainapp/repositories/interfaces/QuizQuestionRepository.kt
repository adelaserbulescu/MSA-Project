package app.msaproject.mainapp.repositories.interfaces

import app.msaproject.mainapp.dtos.quiz.QuizQuestionDTO
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO

interface QuizQuestionRepository {
    suspend fun getAll(): List<QuizQuestionDTO>

    suspend fun getById(id: String): QuizQuestionDTO?

    suspend fun getFiltered(
        quizID: String,
        questionTitle: String,
        questionBody: String,
        sort: String? = null,
        order: String? = null,
        page: Int?
    ): PaginatedResponseDTO<QuizQuestionDTO>
}