package app.msaproject.mainapp.dtos_formatters

import app.msaproject.mainapp.dtos.quiz.QuizDTO
import app.msaproject.mainapp.entities.QuizEntity
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toQuizDTO(): QuizDTO {
    return QuizDTO(
        quizID = this[QuizEntity.quizID],
        countryID = this[QuizEntity.countryID],
        countryGroupID = this[QuizEntity.countryGroupID],
        quizTitle = this[QuizEntity.quizTitle],
        quizDescription = this[QuizEntity.quizDescription],
        imageLink = this[QuizEntity.imageLink],
        score = this[QuizEntity.score]
    )

}