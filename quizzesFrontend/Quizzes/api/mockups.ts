import { QuizDTO, QuizQuestionDTO } from "../types/quiz";

export const quizzes: QuizDTO[] = [
  {
    quizID: "1",
    countryID: "1",
    countryGroupID: "1",
    quizTitle: "History of the Roman Empire",
    quizDescription: "Test your knowledge about Rome"
  },
  {
    quizID: "2",
    countryID: "2",
    countryGroupID: "1",
    quizTitle: "World War II",
    quizDescription: "Quiz on WWII events"
  },
  {
    quizID: "3",
    countryID: "3",
    countryGroupID: "2",
    quizTitle: "Ancient Egypt",
    quizDescription: "Discover the pharaohs and pyramids"
  }
];

export const quizQuestions: QuizQuestionDTO[] = [
  {
    questionID: "1",
    quizID: "1",
    questionTitle: "Who was the first emperor?",
    questionBody: "Name the first emperor of Rome.",
    options: ["Augustus", "Nero", "Julius Caesar", "Caligula"],
    correctAnswer: "Augustus"
  },
  {
    questionID: "2",
    quizID: "1",
    questionTitle: "When did Rome fall?",
    questionBody: "Provide the year of the fall of Rome.",
    options: ["410", "476", "395", "500"],
    correctAnswer: "476"
  },
  {
    questionID: "3",
    quizID: "2",
    questionTitle: "Start of WWII?",
    questionBody: "Which year did WWII start?",
    options: ["1937", "1939", "1941", "1945"],
    correctAnswer: "1939"
  },
  {
    questionID: "4",
    quizID: "2",
    questionTitle: "End of WWII?",
    questionBody: "Which year did WWII end?",
    options: ["1944", "1945", "1946", "1947"],
    correctAnswer: "1945"
  },
  {
    questionID: "5",
    quizID: "3",
    questionTitle: "Famous Pharaoh?",
    questionBody: "Who built the Great Pyramid of Giza?",
    options: ["Tutankhamun", "Ramses II", "Khufu", "Cleopatra"],
    correctAnswer: "Khufu"
  },
  {
    questionID: "6",
    quizID: "3",
    questionTitle: "Egyptian Writing",
    questionBody: "What script did ancient Egyptians use?",
    options: ["Cuneiform", "Hieroglyphs", "Latin", "Greek"],
    correctAnswer: "Hieroglyphs"
  }
];
