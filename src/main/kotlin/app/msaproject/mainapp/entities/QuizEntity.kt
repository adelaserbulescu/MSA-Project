package app.msaproject.mainapp.entities

import org.jetbrains.exposed.dao.id.LongIdTable

enum class quizType{}

object QuizEntity : LongIdTable() {
    val quizID = varchar("quizID", 50)
    val countryID = varchar("countryID", 50)
    val countryGroupID = varchar("countryGroupID", 50)
    val quizTitle = varchar("quizTitle", 50)
    val quizDescription = varchar("quizDescription", 50)
    val imageLink = varchar("imageLink", 50)
    val score = varchar("score", 20)
}


