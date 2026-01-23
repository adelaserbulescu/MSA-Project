import { useState, useEffect, useMemo, useRef } from "react"; // Added useMemo for performance
import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";
import { useLocalSearchParams } from "expo-router";
import LocalCountryCard from "../components/cards/LocalCountryCard";
import { WIKI_SETTINGS } from "../core/Settings"; // Import settings
import { API_URL } from "../core/ApiConfig";

import localData from "../assets/data/countries_info_2025.json";

export default function Wiki() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    const [showLocal, setShowLocal] = useState(true);
    const [sortField, setSortField] = useState("country");
    const [sortDir, setSortDir] = useState("asc");

    // Pagination State
    const scrollRef = useRef(null);
    const [currentPage, setCurrentPage] = useState(1);

    const { search = "", fields = "" } = useLocalSearchParams();
    const searchQuery = search.toLowerCase();
    const activeFields = fields.split(",").filter(Boolean);

    const [cloudData, setCloudData] = useState([]);
    const [cloudError, setCloudError] = useState(null);
    const [showCloud, setShowCloud] = useState(true);

    useEffect(() => {
        fetch(`${API_URL}/countries?page=1&sort=countryName&order=asc`)
            .then(res => res.json())
            .then(json => {
                setCloudData(json.items ?? []); // PaginatedResponseDTO
            })
            .catch(err => {
                setCloudError(err.message);
            });
    }, []);


    // 1. Filter and Sort logic (Memoized to prevent unnecessary recalculations)
    const processedData = useMemo(() => {
        let filtered = localData.filter((country) => {
            if (!searchQuery) return true;
            return activeFields.some((field) => {
                const value = country[field];
                return value && String(value).toLowerCase().includes(searchQuery);
            });
        });

        return filtered.sort((a, b) => {
            let A = a[sortField] ?? "";
            let B = b[sortField] ?? "";
            if (typeof A === 'number' && typeof B === 'number') {
                return sortDir === "asc" ? A - B : B - A;
            }
            return sortDir === "asc"
                ? String(A).localeCompare(String(B))
                : String(B).localeCompare(String(A));
        });
    }, [sortField, sortDir, searchQuery, activeFields]);

    // 2. Pagination Calculations
    const totalPages = Math.ceil(processedData.length / WIKI_SETTINGS.PAGE_LENGTH);
    const startIndex = (currentPage - 1) * WIKI_SETTINGS.PAGE_LENGTH;
    const paginatedLocal = processedData.slice(startIndex, startIndex + WIKI_SETTINGS.PAGE_LENGTH);

    // Generate page numbers logic: [1, ..., curr-2, curr-1, curr, curr+1, curr+2, ..., Last]
    const getPageNumbers = () => {
        const pages = [];
        const range = WIKI_SETTINGS.RANGE;

        for (let i = 1; i <= totalPages; i++) {
            if (
                i === 1 || // Always show first
                i === totalPages || // Always show last
                (i >= currentPage - range && i <= currentPage + range) // Range around current
            ) {
                pages.push(i);
            } else if (pages[pages.length - 1] !== "...") {
                pages.push("...");
            }
        }
        return pages;
    };

    const handlePageChange = (page) => {
        if (typeof page === 'number') {
            setCurrentPage(page);
        }
        scrollRef.current?.scrollTo({
            y: 0,
            animated: false, // This ensures it is instant
        });
    };

    return (
        <ScrollView ref={scrollRef} style={{ backgroundColor: theme.background }}>
            <View style={styles.container}>
                {/* FILTER MENU (Keeping your existing UI) */}
                <Text style={[styles.sectionTitle, { color: theme.text }]}>Filters</Text>
                <View style={styles.row}>
                    {["country", "population", "density", "area", "gdp", "median_age", "continent"].map((field) => (
                        <TouchableOpacity
                            key={field}
                            style={[styles.filterButton, {
                                backgroundColor: sortField === field ? theme.highlighted_background : theme.background,
                                borderColor: theme.text,
                            }]}
                            onPress={() => { setSortField(field); setCurrentPage(1); }} // Reset to page 1 on sort
                        >
                            <Text style={{ color: sortField === field ? theme.highlighted_text : theme.text }}>{field}</Text>
                        </TouchableOpacity>
                    ))}
                </View>

                <View style={styles.row}>
                    <TouchableOpacity
                        activeOpacity={0.7}
                        style={[
                            styles.filterButton,
                            {
                                backgroundColor: theme.highlighted_background,
                                borderColor: theme.text,
                            },
                        ]}
                        onPress={() => {
                            setSortDir((prev) => (prev === "asc" ? "desc" : "asc"));
                            setCurrentPage(1); // Reset pagination
                        }}
                    >
                        <Text style={{ color: theme.highlighted_text, fontWeight: "700" }}>
                            {sortDir === "asc" ? "Order: ▲" : "Order: ▼"}
                        </Text>
                    </TouchableOpacity>
                </View>

                {/* CLOUD DATA SECTION */}
                <TouchableOpacity onPress={() => setShowCloud(!showCloud)}>
                    <Text style={[styles.sectionTitle, { color: theme.text }]}>
                        Cloud Database {showCloud ? "▼" : "▲"}
                    </Text>
                </TouchableOpacity>

                {showCloud && (
                    <>
                        {cloudError && (
                            <Text style={{ color: "red", marginBottom: 10 }}>
                                Error: {cloudError}
                            </Text>
                        )}

                        {cloudData.length === 0 && !cloudError && (
                            <Text style={{ color: theme.text, opacity: 0.5, marginBottom: 10 }}>
                                Loading cloud data...
                            </Text>
                        )}

                        {cloudData.map((country) => (
                            <LocalCountryCard
                                key={`cloud-${country.countryID}`}
                                country={{
                                    tag: country.countryName,
                                    country: country.countryName,
                                    population: "N/A",
                                    density: "N/A",
                                    area: "N/A",
                                    gdp: "N/A",
                                    median_age: "N/A",
                                    continent: "N/A",
                                }}
                            />
                        ))}
                    </>
                )}

                {/* LOCAL DATA SECTION */}
                <TouchableOpacity onPress={() => setShowLocal(!showLocal)}>
                    <Text style={[styles.sectionTitle, { color: theme.text }]}>
                        Archive Records {showLocal ? "▼" : "▲"}
                    </Text>
                </TouchableOpacity>

                {showLocal && (
                    <>
                        {paginatedLocal.map((country) => (
                            <LocalCountryCard key={`local-${country.tag}`} country={country} />
                        ))}

                        {/* PAGINATION CONTROLS */}
                        <View style={styles.paginationContainer}>
                            {getPageNumbers().map((page, index) => (
                                <TouchableOpacity
                                    key={index}
                                    disabled={page === "..."}
                                    onPress={() => handlePageChange(page)}
                                    style={[
                                        styles.pageButton,
                                        currentPage === page && { backgroundColor: theme.highlighted_background }
                                    ]}
                                >
                                    <Text style={[
                                        styles.pageText,
                                        { color: currentPage === page ? theme.highlighted_text : theme.text }
                                    ]}>
                                        {page}
                                    </Text>
                                </TouchableOpacity>
                            ))}
                        </View>
                    </>
                )}
            </View>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: { padding: 16 },
    sectionTitle: { fontSize: 22, fontWeight: "700", marginVertical: 12 },
    row: { flexDirection: "row", flexWrap: "wrap", gap: 10, marginBottom: 12 },
    filterButton: { paddingVertical: 8, paddingHorizontal: 12, borderWidth: 1, borderRadius: 8 },
    placeholder: { marginTop: 20, alignItems: 'center', padding: 20, borderStyle: 'dashed', borderWidth: 1, borderColor: '#888', borderRadius: 10 },
    // New Pagination Styles
    paginationContainer: {
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: 20,
        gap: 5,
        flexWrap: 'wrap'
    },
    pageButton: {
        paddingVertical: 6,
        paddingHorizontal: 10,
        borderRadius: 4,
        minWidth: 35,
        alignItems: 'center'
    },
    pageText: {
        fontSize: 14,
        fontWeight: '600'
    }
});