package app.msaproject.mainapp.dtos_formatters

import app.msaproject.mainapp.dtos.quiz.QuizQuestionDTO
import app.msaproject.mainapp.entities.QuizQuestionEntity
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toQuizQuestionDTO(): QuizQuestionDTO {
    return QuizQuestionDTO(
        questionID = this[QuizQuestionEntity.quizID],
        quizID = this[QuizQuestionEntity.quizID],
        questionTitle = this[QuizQuestionEntity.questionTitle],
        questionBody = this[QuizQuestionEntity.questionBody]
    )

}