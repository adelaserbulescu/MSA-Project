import { View, Text, FlatList, TouchableOpacity, StyleSheet } from "react-native";
import { useEffect, useState } from "react";
import { quizzes as mockQuizzes } from "../../api/mockups";
import { QuizDTO } from "../../types/quiz";
import { useRouter } from "expo-router";

export default function QuizListScreen() {
  const [quizList, setQuizList] = useState<QuizDTO[]>([]);
  const router = useRouter();

  useEffect(() => {
    setQuizList(mockQuizzes);
  }, []);

  return (
    <View style={styles.container}>
      <FlatList
        data={quizList}
        keyExtractor={(item) => item.quizID.toString()}
        renderItem={({ item }) => (
          <TouchableOpacity
            style={styles.itemContainer} // optional padding / background
            onPress={() => router.push(`/screens/questions/${item.quizID}`)}
          >
            <Text style={styles.title}>{item.quizTitle}</Text>
            {item.quizDescription && <Text>{item.quizDescription}</Text>}
          </TouchableOpacity>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#f0f8ff" },
  itemContainer: { padding: 16, borderBottomWidth: 1, borderBottomColor: "#ccc" },
  title: { fontSize: 18, fontWeight: "bold" },
});
