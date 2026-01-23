package app.msaproject.mainapp.dtos_mocks.quiz

import app.msaproject.mainapp.dtos.quiz.QuizQuestionDTO

object MockedQuizQuestionData {

    val questions = listOf(
        QuizQuestionDTO(
            questionID = "rome_q1",
            quizID = "quiz_rome_01",
            questionTitle = "Founding of Rome",
            questionBody = "In which year was Rome traditionally founded?"
        ),
        QuizQuestionDTO(
            questionID = "rome_q2",
            quizID = "quiz_rome_01",
            questionTitle = "First Emperor",
            questionBody = "Who was the first Roman Emperor?"
        ),
        QuizQuestionDTO(
            questionID = "ww2_q1",
            quizID = "quiz_ww2_01",
            questionTitle = "Start of the War",
            questionBody = "In which year did World War II begin?"
        ),
        QuizQuestionDTO(
            questionID = "ww2_q2",
            quizID = "quiz_ww2_01",
            questionTitle = "Pearl Harbor",
            questionBody = "Which event caused the USA to enter World War II?"
        ),
        QuizQuestionDTO(
            questionID = "ro_q1",
            quizID = "quiz_romania_01",
            questionTitle = "Unification",
            questionBody = "In what year did the unification of the Romanian principalities occur?"
        ),
        QuizQuestionDTO(
            questionID = "yu_q1",
            quizID = "quiz_yugoslavia_01",
            questionTitle = "Formation",
            questionBody = "In which year was Yugoslavia formed?"
        )
    )
}
