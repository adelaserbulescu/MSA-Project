// src/navigation/AppNavigator.tsx
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import QuizListScreen from "../screens/quizzes";
import QuizQuestionsScreen from "../screens/questions/[quizID].tsx";

const Stack = createNativeStackNavigator();

export default function AppNavigator() {
  return (
    <Stack.Navigator>
      <Stack.Screen name="Quizzes" component={QuizListScreen} />
      <Stack.Screen name="Questions" component={QuizQuestionsScreen} />
    </Stack.Navigator>
  );
}
