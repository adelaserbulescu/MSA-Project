package app.msaproject.mainapp.entities

import org.jetbrains.exposed.dao.id.LongIdTable

enum class quizQuestionType {
    MULTIPLE_CHOICE,
    SINGLE_CHOICE,
    MATCHING_QUESTION,
    FILL_IN_THE_BLANK
}

object QuizQuestionEntity : LongIdTable() {
    val questionID = varchar("questionID", 50)
    val quizID = varchar("quizID", 50)
    val questionTitle = varchar("questionTitle", 50)
    val questionBody = varchar("questionBody", 50)
}