import { View, Text, StyleSheet, useColorScheme } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";


export default function Medias() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    return (
        <View style={[styles.container, { backgroundColor: theme.background }]}>
            <Text style={[styles.title, { color: theme.text }]}>Medias</Text>
            <Text style={[styles.subtitle, { color: theme.textSecondary || theme.text }]}>
                This section will contain videos, images, and other media content.
            </Text>
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
    title: {
        fontSize: 26,
        fontWeight: "700",
        marginBottom: 10,
    },
    subtitle: {
        fontSize: 16,
        textAlign: "center",
        maxWidth: 300,
    },
});
