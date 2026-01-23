package app.msaproject.mainapp.repositories.implementations

import app.msaproject.mainapp.configs.PaginationConfig
import app.msaproject.mainapp.dtos.pagination.PaginatedResponseDTO
import app.msaproject.mainapp.dtos.quiz.QuizQuestionDTO
import app.msaproject.mainapp.dtos_mocks.quiz.MockedQuizQuestionData
import app.msaproject.mainapp.repositories.interfaces.QuizQuestionRepository
import app.msaproject.mainapp.utils.PaginationUtils

class QuizQuestionRepositoryImpl : QuizQuestionRepository {
    private val questions = MockedQuizQuestionData.questions

    override suspend fun getAll(): List<QuizQuestionDTO> {
        return questions
    }

    override suspend fun getById(id: String): QuizQuestionDTO? {
        return questions.find{ it.questionID == id }
    }

    override suspend fun getFiltered(
        quizID: String,
        questionTitle: String,
        questionBody: String,
        sort: String?,
        order: String?,
        page: Int?
    ): PaginatedResponseDTO<QuizQuestionDTO> {
        val fixedPageSize = PaginationConfig.quizQuestionPageLimit

        var result = questions.filter{ c ->
            val quizIDMatches = quizID.let{ c.quizID.contains(it, ignoreCase = true) }

            val questionTitleMatches = questionTitle.let{ c.questionTitle.contains(it, ignoreCase = true) }

            val questionBodyMatches = questionBody.let{ c.questionBody.contains(it, ignoreCase = true) }

            quizIDMatches && questionTitleMatches && questionBodyMatches

        }

        if(sort != null) {
            result = when(sort) {
                "quizID" -> result.sortedBy{ it.quizID }
                "questionTitle" -> result.sortedBy { it.questionTitle }
                "questionBody" -> result.sortedBy { it.questionBody }
                else -> result
            }
        }

        if(order == "desc") {
            result = result.reversed()
        }

        return PaginationUtils.paginate(
            result,
            page = page,
            pageSize = fixedPageSize
        )
    }
}