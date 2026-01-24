export interface QuizDTO {
  quizID: string;
  countryID?: string;
  countryGroupID?: string;
  quizTitle: string;
  quizDescription?: string;
  imageLink?: string;
  score?: string;
}

export interface QuizQuestionDTO {
  questionID: string;
  quizID: string;
  questionTitle: string;
  questionBody: string;
  options: string[]
  correctAnswer: string
}
