import { View, Text, StyleSheet, TouchableOpacity, useColorScheme } from "react-native";
import { useThemeMode } from "../core/ThemeContext";
import { Colors } from "../core/Colors";
import { availableLanguages } from "../core/Languages";
import { useState } from "react";

export default function Settings() {
    const systemScheme = useColorScheme();

    // Global theme override
    const { override, setOverride, activeTheme } = useThemeMode();

    // Local language state (you can later move this to context too)
    const [language, setLanguage] = useState("en");

    const theme = Colors[activeTheme];

    function resetDefaults() {
        setOverride("system");
        setLanguage("en");
    }

    return (
        <View style={[styles.container, { backgroundColor: theme.background }]}>
            <Text style={[styles.title, { color: theme.text }]}>Settings</Text>

            {/* THEME SELECTOR */}
            <View style={styles.section}>
                <Text style={[styles.label, { color: theme.text }]}>Theme Mode</Text>

                <View style={styles.row}>
                    {["system", "light", "dark"].map((mode) => {
                        const isActive = override === mode;

                        return (
                            <TouchableOpacity
                                key={mode}
                                style={[
                                    styles.option,
                                    {
                                        backgroundColor: isActive
                                            ? theme.highlighted_background
                                            : theme.background,
                                        borderColor: theme.text,
                                    },
                                ]}
                                onPress={() => setOverride(mode)}
                            >
                                <Text
                                    style={{
                                        color: isActive ? theme.highlighted_text : theme.text,
                                        fontWeight: isActive ? "700" : "500",
                                    }}
                                >
                                    {mode.charAt(0).toUpperCase() + mode.slice(1)}
                                </Text>
                            </TouchableOpacity>
                        );
                    })}
                </View>
            </View>

            {/* LANGUAGE SELECTOR */}
            <View style={styles.section}>
                <Text style={[styles.label, { color: theme.text }]}>Language</Text>

                <View style={styles.row}>
                    {availableLanguages.map((lang) => {
                        const isActive = language === lang.code;

                        return (
                            <TouchableOpacity
                                key={lang.code}
                                style={[
                                    styles.option,
                                    {
                                        backgroundColor: isActive
                                            ? theme.highlighted_background
                                            : theme.background,
                                        borderColor: theme.text,
                                    },
                                ]}
                                onPress={() => setLanguage(lang.code)}
                            >
                                <Text
                                    style={{
                                        color: isActive ? theme.highlighted_text : theme.text,
                                        fontWeight: isActive ? "700" : "500",
                                    }}
                                >
                                    {lang.label}
                                </Text>
                            </TouchableOpacity>
                        );
                    })}
                </View>
            </View>

            {/* RESET BUTTON */}
            <TouchableOpacity
                style={[
                    styles.resetButton,
                    { backgroundColor: theme.highlighted_background },
                ]}
                onPress={resetDefaults}
            >
                <Text
                    style={[
                        styles.resetText,
                        { color: theme.highlighted_text },
                    ]}
                >
                    Reset to Defaults
                </Text>
            </TouchableOpacity>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 25,
    },
    title: {
        fontSize: 28,
        fontWeight: "700",
        marginBottom: 30,
        textAlign: "center",
    },
    section: {
        marginBottom: 35,
    },
    label: {
        fontSize: 18,
        fontWeight: "600",
        marginBottom: 12,
    },
    row: {
        flexDirection: "row",
        gap: 10,
        flexWrap: "wrap",
    },
    option: {
        paddingVertical: 10,
        paddingHorizontal: 18,
        borderWidth: 1,
        borderRadius: 10,
    },
    resetButton: {
        marginTop: 40,
        paddingVertical: 14,
        borderRadius: 12,
        alignItems: "center",
    },
    resetText: {
        fontSize: 16,
        fontWeight: "700",
    },
});
