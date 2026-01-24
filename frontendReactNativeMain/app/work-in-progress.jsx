import { View, Text, StyleSheet, Image, TouchableOpacity } from "react-native";
import { useRouter } from "expo-router";
import { useColorScheme } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";


export default function WorkInProgress() {
    const router = useRouter();
    const scheme = useColorScheme();
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    return (
        <View style={[styles.container, { backgroundColor: theme.background }]}>
            <View style={[styles.card, { backgroundColor: theme.card || theme.background }]}>

                <Image
                    source={require("../assets/logo.png")}
                    style={styles.logo}
                    resizeMode="contain"
                />

                <Text style={[styles.title, { color: theme.text }]}>
                    🏗️ Work In Progress 🏗️
                </Text>

                <Text style={[styles.message, { color: theme.textSecondary || theme.text }]}>
                    This page is not ready yet. Check back soon.
                </Text>

                <TouchableOpacity
                    style={[styles.button, { backgroundColor: theme.primary || "#444" }]}
                    onPress={() => router.push("/home")}
                >
                    <Text style={[styles.buttonText, { color: theme.buttonText || "#fff" }]}>
                        Return Home
                    </Text>
                </TouchableOpacity>

            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        padding: 20,
    },
    card: {
        width: "100%",
        maxWidth: 350,
        borderRadius: 16,
        padding: 25,
        alignItems: "center",
        elevation: 4,
    },
    logo: {
        width: 120,
        height: 120,
        marginBottom: 20,
    },
    title: {
        fontSize: 22,
        fontWeight: "700",
        marginBottom: 10,
    },
    message: {
        fontSize: 16,
        textAlign: "center",
        marginBottom: 25,
    },
    button: {
        paddingVertical: 12,
        paddingHorizontal: 25,
        borderRadius: 10,
        marginTop: 10,
    },
    buttonText: {
        fontSize: 16,
        fontWeight: "600",
    },
});
