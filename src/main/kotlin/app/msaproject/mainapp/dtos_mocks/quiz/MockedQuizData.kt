package app.msaproject.mainapp.dtos_mocks.quiz

import app.msaproject.mainapp.dtos.quiz.QuizDTO

object MockedQuizData {

    val quizzes = listOf(
        QuizDTO(
            quizID = "quiz_rome_01",
            countryID = "3",
            countryGroupID = "1",
            quizTitle = "Roman Empire Basics",
            quizDescription = "Test your knowledge about Ancient Rome",
            imageLink = "rome_banner.png",
            score = "standard"
        ),
        QuizDTO(
            quizID = "quiz_ww2_01",
            countryID = "1",
            countryGroupID = "1",
            quizTitle = "World War II Overview",
            quizDescription = "Key events of World War II",
            imageLink = "ww2_banner.png",
            score = "standard"
        ),
        QuizDTO(
            quizID = "quiz_romania_01",
            countryID = "4",
            countryGroupID = "2",
            quizTitle = "Modern Romania",
            quizDescription = "Important moments in Romanian history",
            imageLink = "romania_banner.png",
            score = "easy"
        ),
        QuizDTO(
            quizID = "quiz_yugoslavia_01",
            countryID = "5",
            countryGroupID = "2",
            quizTitle = "Yugoslavia and the Balkans",
            quizDescription = "History of Yugoslavia and its dissolution",
            imageLink = "yugoslavia_banner.png",
            score = "hard"
        )
    )
}
