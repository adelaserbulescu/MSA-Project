import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from "react-native";
import { Image } from "expo-image";
import { useRouter } from "expo-router";

export default function HomeScreen() {
  const router = useRouter();

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.header}>History Quiz App</Text>

      {/* Logo */}
      <Image
        source={require("@/assets/images/logo.jpg")}
        style={styles.logo}
      />

      {/* Welcome / Intro */}
      <View style={styles.section}>
        <Text style={styles.title}>Test Your Knowledge!</Text>
        <Text style={styles.subtitle}>
          Explore history through interactive quizzes. Challenge yourself and see how much you know!
        </Text>
      </View>

      {/* Quizzes module button */}
      <View style={styles.section}>
        <TouchableOpacity
          style={styles.moduleButton}
          onPress={() => router.push("/screens/quizzes")}
        >
          <Text style={styles.moduleTitle}>Let's go!</Text>
          <Text style={styles.moduleDesc}>
            Participate in history quizzes.
          </Text>
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 24,
    backgroundColor: "#f0f8ff",
    flexGrow: 1,
  },
  header: {
    fontSize: 32,
    fontWeight: "bold",
    textAlign: "center",
    marginBottom: 16,
  },
  logo: {
    width: 200,
    height: 120,
    resizeMode: "contain",
    alignSelf: "center",
    marginBottom: 24,
  },
  section: {
    marginBottom: 32,
  },
  title: {
    fontSize: 24,
    fontWeight: "600",
    marginBottom: 8,
  },
  subtitle: {
    fontSize: 16,
    lineHeight: 22,
    color: "#333",
  },
  moduleButton: {
    backgroundColor: "#fff",
    padding: 20,
    borderRadius: 12,
    shadowColor: "#000",
    shadowOpacity: 0.1,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 2 },
  },
  moduleTitle: {
    fontSize: 18,
    fontWeight: "bold",
  },
  moduleDesc: {
    fontSize: 14,
    marginTop: 4,
    color: "#555",
  },
});
