import React from "react";
import { View, Text, StyleSheet, ScrollView, TouchableOpacity, Linking } from "react-native";
import { useColorScheme } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";


export default function Contact() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    return (
        <ScrollView style={{ backgroundColor: theme.background }} contentContainerStyle={styles.container}>
            <Text style={[styles.title, { color: theme.text }]}>Contact</Text>

            <View style={styles.section}>
                <Text style={[styles.heading, { color: Colors.accent1 }]}>Support Archive</Text>
                <Text style={[styles.body, { color: theme.text }]}>
                    Have you discovered a temporal anomaly or a factual error in our database?
                    Reach out to our lead archivists.
                </Text>
            </View>

            <View style={styles.section}>
                <Text style={[styles.heading, { color: Colors.accent1 }]}>Email</Text>
                <TouchableOpacity onPress={() => Linking.openURL('mailto:support@chronolock.app')}>
                    <Text style={[styles.linkText, { color: Colors.accent0 }]}>support@chronolock.app</Text>
                </TouchableOpacity>
            </View>

            <View style={styles.section}>
                <Text style={[styles.heading, { color: Colors.accent1 }]}>Social Frequency</Text>
                <Text style={[styles.body, { color: theme.text }]}>@ChronoLockApp</Text>
            </View>

            <Text style={[styles.footer, { color: Colors.accent0 }]}>Response time: ~24 hours (Linear Time)</Text>
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
    linkText: {
        fontSize: 16,
        fontWeight: "600",
        textDecorationLine: "underline",
    },
    footer: {
        marginTop: 40,
        textAlign: "center",
        fontSize: 12,
    }
});