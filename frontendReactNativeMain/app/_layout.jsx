import { Stack } from "expo-router";
import { View, StyleSheet } from "react-native";
import Header from "../components/Layout/Header";
import Footer from "../components/Layout/Footer";
import LoadingScreen from "../components/Layout/LoadingScreen";
import { ThemeProvider } from "../core/ThemeContext";
import { useEffect, useState } from "react";

export default function RootLayout() {
    const [isReady, setIsReady] = useState(false);

    useEffect(() => {
        // Simulate loading tasks (fonts, CSV, assets, etc.)
        const timer = setTimeout(() => {
            setIsReady(true);
        }, 1500);

        return () => clearTimeout(timer);
    }, []);

    if (!isReady) {
        return <LoadingScreen />;
    }

    return (
        <ThemeProvider>
            <View style={styles.container}>
                <Header />

                <View style={styles.content}>
                    <Stack
                        screenOptions={{
                            headerShown: false,
                            animation: "slide_from_right",
                            gestureEnabled: true,
                        }}
                    />
                </View>

                <Footer />
            </View>
        </ThemeProvider>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    content: {
        flex: 1,
    },
});
