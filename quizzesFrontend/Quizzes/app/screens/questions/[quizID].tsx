import { View, Text, FlatList, TouchableOpacity, StyleSheet } from "react-native";
import { useEffect, useState } from "react";
import { useLocalSearchParams } from "expo-router";
import { quizQuestions, QuizQuestionDTO } from "../../../api/mockups";

export default function QuizQuestionsScreen() {
  const { quizID } = useLocalSearchParams<{ quizID: string }>();
  const [questions, setQuestions] = useState<QuizQuestionDTO[]>([]);
  const [selectedAnswers, setSelectedAnswers] = useState<{[key: string]: string}>({});

  useEffect(() => {
    if (!quizID) return;

    const filtered = quizQuestions.filter(q => q.quizID.toString() === quizID.toString());
    setQuestions(filtered);
  }, [quizID]);

  const handleAnswer = (questionID: string, selected: string, correctAnswer: string) => {
    setSelectedAnswers(prev => ({ ...prev, [questionID]: selected }));

    if (selected === correctAnswer) {
      alert("Correct!");
    } else {
      alert(`Wrong! Correct answer: ${correctAnswer}`);
    }
  };

  return (
    <View style={styles.container}>
      <FlatList
        data={questions}
        keyExtractor={(item) => item.questionID.toString()}
        renderItem={({ item }) => {
          const selected = selectedAnswers[item.questionID];
          return (
            <View style={styles.questionCard}>
              <Text style={styles.questionTitle}>{item.questionTitle}</Text>
              <Text style={styles.questionBody}>{item.questionBody}</Text>

              {item.options.map(option => (
                <TouchableOpacity
                  key={option}
                  style={[
                    styles.optionButton,
                    selected === option
                      ? option === item.correctAnswer
                        ? styles.correct
                        : styles.wrong
                      : null
                  ]}
                  onPress={() => handleAnswer(item.questionID, option, item.correctAnswer)}
                  disabled={!!selected}
                >
                  <Text style={styles.optionText}>{option}</Text>
                </TouchableOpacity>
              ))}
            </View>
          );
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#f0f8ff", padding: 16 },
  questionCard: {
    backgroundColor: "#fff",
    padding: 16,
    borderRadius: 12,
    marginBottom: 16,
    shadowColor: "#000",
    shadowOpacity: 0.1,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 2 },
  },
  questionTitle: { fontWeight: "bold", fontSize: 16, marginBottom: 4 },
  questionBody: { fontSize: 14, marginBottom: 12 },
  optionButton: {
    backgroundColor: "#e0f0ff",
    padding: 12,
    borderRadius: 8,
    marginBottom: 8,
  },
  optionText: { fontSize: 14 },
  correct: { backgroundColor: "#c8f7c5" },
  wrong: { backgroundColor: "#f7c5c5" },
});
