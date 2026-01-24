import React, { useEffect, useRef, useState } from "react";
import { View, Text, StyleSheet, Animated, useColorScheme } from "react-native";
import { Colors } from "../core/Colors";
import { historyTranslations } from "../core/Languages";
import { useThemeMode } from "../core/ThemeContext";


export default function Home() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    // --- Animation Values ---
    const floatAnim = useRef(new Animated.Value(0)).current;

    // --- Typewriter State ---
    const [displayText, setDisplayText] = useState("");
    const [langIndex, setLangIndex] = useState(0);
    const [isDeleting, setIsDeleting] = useState(false);
    const [typingSpeed, setTypingSpeed] = useState(150);

    // 1. Floating Logo Animation
    useEffect(() => {
        Animated.loop(
            Animated.sequence([
                Animated.timing(floatAnim, {
                    toValue: -15,
                    duration: 2500,
                    useNativeDriver: true,
                }),
                Animated.timing(floatAnim, {
                    toValue: 0,
                    duration: 2500,
                    useNativeDriver: true,
                }),
            ])
        ).start();
    }, [floatAnim]);

    // 2. Typewriter Effect Logic
    useEffect(() => {
        const currentFullWord = historyTranslations[langIndex];

        const handleTyping = () => {
            if (!isDeleting) {
                // Adding characters
                setDisplayText(currentFullWord.substring(0, displayText.length + 1));
                setTypingSpeed(150);

                if (displayText === currentFullWord) {
                    // Pause at the end of the word
                    setIsDeleting(true);
                    setTypingSpeed(2000);
                }
            } else {
                // Removing characters
                setDisplayText(currentFullWord.substring(0, displayText.length - 1));
                setTypingSpeed(75);

                if (displayText === "") {
                    setIsDeleting(false);
                    setLangIndex((prev) => (prev + 1) % historyTranslations.length);
                    setTypingSpeed(500);
                }
            }
        };

        const timer = setTimeout(handleTyping, typingSpeed);
        return () => clearTimeout(timer);
    }, [displayText, isDeleting, langIndex, typingSpeed]);

    return (
        <View style={[styles.container, { backgroundColor: theme.background }]}>
            <Animated.Image
                source={require("../assets/logo-text.png")}
                style={[
                    styles.logo,
                    { transform: [{ translateY: floatAnim }] },
                ]}
                resizeMode="contain"
            />

            <View style={styles.textRow}>
                <Text style={[styles.text, { color: theme.text }]}>
                    Welcome to your{" "}
                </Text>
                <Text style={[styles.text, styles.highlight]}>
                    {displayText}
                    <Text style={styles.cursor}>|</Text>
                </Text>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
    logo: {
        width: 220,
        height: 220,
        marginBottom: 10,
    },
    textRow: {
        flexDirection: "row",
        alignItems: "center",
    },
    text: {
        fontSize: 22,
        fontWeight: "600",
    },
    highlight: {
        color: Colors.accent1,
        fontWeight: "bold",
    },
    cursor: {
        fontWeight: "100",
        color: Colors.accent1,
    }
});