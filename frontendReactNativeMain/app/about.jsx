import { View, Text, StyleSheet, ScrollView, Image } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";

export default function About() {

    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    const teamLogo =
        activeTheme === "dark"
            ? require("../assets/team-logo-dark.png")
            : require("../assets/team-logo-light.png");

    return (
        <ScrollView
            style={{ backgroundColor: theme.background }}
            contentContainerStyle={styles.container}
        >
            <Text style={[styles.title, { color: theme.text }]}>ChronoLock</Text>

            <View style={styles.section}>
                <Text style={[styles.heading, { color: Colors.accent1 }]}>Our Vision</Text>
                <Text style={[styles.body, { color: theme.text }]}>
                    History is more than just dates on a page; it is the collective memory of humanity.
                    ChronoLock was built to bridge the gap between static textbooks and the dynamic
                    reality of our past.
                </Text>
            </View>

            <Text style={[styles.footer, { color: Colors.accent0 }]}>
                Version 0.1.2 • ChronoLock App
            </Text>

            {/* MADE BY + TEAM LOGO */}
            <View style={styles.madeByContainer}>
                <Text style={[styles.madeByText, { color: theme.textSecondary || theme.text }]}>
                    Made by
                </Text>
                <Image source={teamLogo} style={styles.teamLogo} />
            </View>
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
    },

    // NEW STYLES
    madeByContainer: {
        marginTop: 30,
        alignItems: "center",
        justifyContent: "center",
    },
    madeByText: {
        fontSize: 14,
        opacity: 0.8,
    },
    teamLogo: {
        width: 240,
        height: 240,
        resizeMode: "contain",
        opacity: 0.9,
    },
});
