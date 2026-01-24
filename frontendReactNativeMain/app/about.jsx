import { View, Text, StyleSheet, ScrollView } from "react-native";
import { useColorScheme } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";

export default function About() {

    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];


    return (
        <ScrollView style={{ backgroundColor: theme.background }} contentContainerStyle={styles.container}>
            <Text style={[styles.title, { color: theme.text }]}>ChronoLock</Text>

            <View style={styles.section}>
                <Text style={[styles.heading, { color: Colors.accent1 }]}>Our Vision</Text>
                <Text style={[styles.body, { color: theme.text }]}>
                    History is more than just dates on a page; it is the collective memory of humanity.
                    ChronoLock was built to bridge the gap between static textbooks and the dynamic
                    reality of our past.
                </Text>
            </View>

            <View style={styles.section}>
                <Text style={[styles.heading, { color: Colors.accent1 }]}>The Mission</Text>
                <Text style={[styles.body, { color: theme.text }]}>
                    By transforming the traditional wiki into a fluid, interactive database,
                    we aim to modernize history lessons for students and enthusiasts around the globe,
                    making the past more accessible than ever before.
                </Text>
            </View>

            <Text style={[styles.footer, { color: Colors.accent0 }]}>Version 0.1.2 • ChronoLock App</Text>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        paddingTop: 80,
        paddingHorizontal: 25,
        paddingBottom: 40,
    },
    title: {
        fontSize: 32,
        fontWeight: "800",
        marginBottom: 30,
        textAlign: "center",
        letterSpacing: 1,
    },
    section: {
        marginBottom: 25,
    },
    heading: {
        fontSize: 18,
        fontWeight: "bold",
        marginBottom: 8,
        textTransform: "uppercase",
    },
    body: {
        fontSize: 16,
        lineHeight: 24,
        opacity: 0.8,
    },
    footer: {
        marginTop: 40,
        textAlign: "center",
        fontSize: 12,
    }
});