import React from "react";
import { View, Text, StyleSheet, ScrollView } from "react-native";
import { useColorScheme } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";


export default function Terms() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    const articles = [
        {
            title: "1. The Grandfather Paradox Clause",
            content: "By using this app, you agree not to travel back in time to prevent your own birth. Doing so will result in an immediate account suspension and a very awkward family dinner."
        },
        {
            title: "2. Spoilers of the Future",
            content: "Users are strictly prohibited from using the 'History of Next Week' feature to place bets on sporting events, lottery numbers, or whether or not your roommate finally does the dishes."
        },
        {
            title: "3. Butterfly Effect Liability",
            content: "ChronoLock is not responsible for any accidental changes to the present. If you wake up and find that humans have been replaced by highly intelligent squirrels, please check your 'Recent History' logs and undo your last action."
        },
        {
            title: "4. Temporal Lag",
            content: "While we aim for instant loading, sometimes data from the Jurassic period takes a while to arrive. Please do not refresh the app repeatedly; the dinosaurs are dead, they aren't going anywhere."
        },
        {
            title: "5. Subscription Fees",
            content: "Standard accounts are free. Premium accounts cost $9.99/month, or a one-time payment of 50 gold doubloons if you are accessing the app from the year 1712."
        },
        {
            title: "6. User Conduct",
            content: "Harassing historical figures is prohibited. Do not send 'u up?' messages to Napoleon Bonaparte or try to explain NFTs to Leonardo da Vinci. It won't end well for anyone."
        }
    ];

    return (
        <ScrollView style={{ backgroundColor: theme.background }} contentContainerStyle={styles.container}>
            <Text style={[styles.title, { color: theme.text }]}>Temporal Terms</Text>
            <Text style={[styles.subtitle, { color: Colors.accent1 }]}>Please read carefully</Text>

            {articles.map((item, index) => (
                <View key={index} style={styles.section}>
                    <Text style={[styles.heading, { color: Colors.accent1 }]}>{item.title}</Text>
                    <Text style={[styles.body, { color: theme.text }]}>{item.content}</Text>
                </View>
            ))}

            <View style={styles.finePrint}>
                <Text style={[styles.footer, { color: Colors.accent1 }]}>
                    * ChronoLock reserves the right to change these terms in the past, present, or future without telling you. You probably already agreed to them yesterday.
                </Text>
            </View>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        paddingTop: 80,
        paddingHorizontal: 25,
        paddingBottom: 60,
    },
    title: {
        fontSize: 32,
        fontWeight: "800",
        textAlign: "center",
        letterSpacing: 1,
    },
    subtitle: {
        fontSize: 14,
        fontStyle: "italic",
        textAlign: "center",
        marginBottom: 30,
        marginTop: 5,
    },
    section: {
        marginBottom: 25,
        padding: 15,
        borderRadius: 12,
        backgroundColor: 'rgba(0,0,0,0.03)', // Subtle box for each "law"
    },
    heading: {
        fontSize: 18,
        fontWeight: "bold",
        marginBottom: 8,
        textTransform: "uppercase",
    },
    body: {
        fontSize: 15,
        lineHeight: 22,
        opacity: 0.9,
    },
    finePrint: {
        marginTop: 20,
        borderTopWidth: 1,
        borderTopColor: 'rgba(150,150,150,0.2)',
        paddingTop: 20,
    },
    footer: {
        textAlign: "center",
        fontSize: 12,
        lineHeight: 18,
    }
});