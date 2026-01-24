import { useState } from "react";
import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";
import LocalCountryCard from "../components/Layout/cards/LocalCountryCard";

// import cloudData from "../assets/data/cloud_data_mock.json" // Disabled for now
import localData from "../assets/data/countries_info_2025.json"

export default function Wiki() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    // cloudData is disabled, so we default showCloud to false or remove it
    const [showLocal, setShowLocal] = useState(true);

    const [sortField, setSortField] = useState("country");
    const [sortDir, setSortDir] = useState("asc");

    const sortData = (data) => {
        if (!data || !Array.isArray(data)) return [];

        return [...data].sort((a, b) => {
            let A = a[sortField];
            let B = b[sortField];

            // Ensure we don't compare undefined values
            if (A === undefined || A === null) A = "";
            if (B === undefined || B === null) B = "";

            if (typeof A === 'number' && typeof B === 'number') {
                return sortDir === "asc" ? A - B : B - A;
            }

            return sortDir === "asc"
                ? String(A).localeCompare(String(B))
                : String(B).localeCompare(String(A));
        });
    };

    const sortedLocal = sortData(localData);

    return (
        <ScrollView style={{ backgroundColor: theme.background }}>
            <View style={styles.container}>

                {/* FILTER MENU */}
                <Text style={[styles.sectionTitle, { color: theme.text }]}>Filters</Text>

                <View style={styles.row}>
                    {["country", "population", "density", "area", "gdp", "median_age", "continent"].map((field) => (
                        <TouchableOpacity
                            key={field}
                            activeOpacity={0.7}
                            style={[
                                styles.filterButton,
                                {
                                    backgroundColor: sortField === field ? theme.highlighted_background : theme.background,
                                    borderColor: theme.text,
                                },
                            ]}
                            onPress={() => setSortField(field)}
                        >
                            <Text style={{ color: sortField === field ? theme.highlighted_text : theme.text }}>
                                {field}
                            </Text>
                        </TouchableOpacity>
                    ))}
                </View>

                <View style={styles.row}>
                    {["asc", "desc"].map((dir) => (
                        <TouchableOpacity
                            key={dir}
                            activeOpacity={0.7}
                            style={[
                                styles.filterButton,
                                {
                                    backgroundColor: sortDir === dir ? theme.highlighted_background : theme.background,
                                    borderColor: theme.text,
                                },
                            ]}
                            onPress={() => setSortDir(dir)}
                        >
                            <Text style={{ color: sortDir === dir ? theme.highlighted_text : theme.text }}>
                                {dir.toUpperCase()}
                            </Text>
                        </TouchableOpacity>
                    ))}
                </View>

                {/* LOCAL DATA SECTION */}
                <TouchableOpacity onPress={() => setShowLocal(!showLocal)}>
                    <Text style={[styles.sectionTitle, { color: theme.text }]}>
                        Archive Records {showLocal ? "▼" : "▲"}
                    </Text>
                </TouchableOpacity>

                {showLocal &&
                    sortedLocal.map((country) => (
                        // Added prefix to key to ensure uniqueness
                        <LocalCountryCard key={`local-${country.id}`} country={country} />
                    ))}

                {/* Placeholder for future Cloud Data */}
                <View style={styles.placeholder}>
                    <Text style={{ color: theme.text, opacity: 0.5 }}>Cloud Database Offline</Text>
                </View>
            </View>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        padding: 16,
    },
    sectionTitle: {
        fontSize: 22,
        fontWeight: "700",
        marginVertical: 12,
    },
    row: {
        flexDirection: "row",
        flexWrap: "wrap",
        gap: 10,
        marginBottom: 12,
    },
    filterButton: {
        paddingVertical: 8,
        paddingHorizontal: 12,
        borderWidth: 1,
        borderRadius: 8,
    },
    placeholder: {
        marginTop: 20,
        alignItems: 'center',
        padding: 20,
        borderStyle: 'dashed',
        borderWidth: 1,
        borderColor: '#888',
        borderRadius: 10,
    }
});