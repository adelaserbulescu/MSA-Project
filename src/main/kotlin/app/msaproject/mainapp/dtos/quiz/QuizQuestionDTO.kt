package app.msaproject.mainapp.dtos.quiz

import kotlinx.serialization.Serializable

@Serializable
data class QuizQuestionDTO(
    val questionID: String,
    val quizID: String,
    val questionTitle: String,
    val questionBody: String,
)