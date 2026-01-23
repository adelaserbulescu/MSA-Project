package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.quiz.QuizDTO
import app.msaproject.mainapp.repositories.interfaces.QuizRepository
import app.msaproject.mainapp.dtos_mocks.quiz.MockedQuizData
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.utils.PaginationUtils

class QuizRepositoryImpl : QuizRepository {

    private val quizzes = MockedQuizData.quizzes

    override suspend fun getAll() : List<QuizDTO> {
        return quizzes
    }

    override suspend fun getById(id: String): QuizDTO? {
        return quizzes.find{ it.quizID?.equals(id) == true }
    }

    override suspend fun getFiltered(
        countryID: String?,
        countryGroupID: String?,
        quizTitle: String?,
        quizDescription: String?,
        sort: String?,
        order: String?,
        page: Int?
    ): PaginatedResponseDTO<QuizDTO> {
        val fixedPageSize = PaginationConfig.quizPageLimit

        var result = quizzes.filter { c ->
            val countryIdMatches = countryID?.let { c.countryID?.contains(it, ignoreCase = true) } ?: true

            val countryGroupIdMatches = countryGroupID?.let { c.countryGroupID?.equals(it) == true } ?: true

            val quizTitleMatches = quizTitle?.let { c.quizTitle.contains(it, ignoreCase = true) } ?: true

            val quizDescriptionMatches = quizDescription?.let { c.quizDescription?.contains(it, ignoreCase = true) ?: true } ?: true

            countryIdMatches && countryGroupIdMatches && quizTitleMatches && quizDescriptionMatches

        }

        if(sort != null) {
            result = when (sort) {
                "countryID" -> result.sortedBy {it.countryID}
                "countryGroupID" -> result.sortedBy{it.countryGroupID}
                "quizTitle" -> result.sortedBy {it.quizTitle}
                "quizDescription" -> result.sortedBy {it.quizDescription}
                else -> result
            }

            if(order == "desc") {
                result = result.reversed()
            }
        }
        return PaginationUtils.paginate(
            list = result,
            page = page,
            pageSize = fixedPageSize,
        )

    }
}