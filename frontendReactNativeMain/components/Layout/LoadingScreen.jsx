import { useEffect, useState } from "react";
import { View, Text, Image, StyleSheet, useColorScheme } from "react-native";
import { Colors } from "../../core/Colors";

export default function LoadingScreen() {
    const [dots, setDots] = useState(".");
    const [feedback, setFeedback] = useState("");

    const scheme = useColorScheme();
    const theme = Colors[scheme] || Colors.light;

    useEffect(() => {
        const interval = setInterval(() => {
            setDots(prev => (prev === "..." ? "." : prev + "."));
        }, 500);

        return () => clearInterval(interval);
    }, []);

    return (
        <View style={[styles.container, { backgroundColor: theme.background }]}>
            <Image
                source={require("../../assets/logo.png")}
                style={styles.logo}
                resizeMode="contain"
            />

            <Text style={[styles.title, { color: theme.text }]}>
                Loading{dots}
            </Text>

            <Text style={[styles.feedback, { color: theme.textSecondary || theme.text }]}>
                {feedback}
            </Text>
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
        width: 150,
        height: 150,
        marginBottom: 20,
    },
    title: {
        fontSize: 22,
        fontWeight: "600",
        marginBottom: 10,
    },
    feedback: {
        fontSize: 16,
        marginTop: 10,
    },
});
