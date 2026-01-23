package app.msaproject.mainapp.dtos.quiz
import kotlinx.serialization.Serializable

@Serializable
data class QuizDTO(
    val quizID: String? = null,
    val countryID: String? = null,
    val countryGroupID: String? = null,
    val quizTitle: String,
    val quizDescription: String?,
    val imageLink: String? = null,
    val score: String? = null,
)