// components/LocalCountryCard.jsx
import { View, Text, StyleSheet, Image, TouchableOpacity, Linking } from "react-native";
import { Colors } from "../../../core/Colors";
import { useThemeMode } from "../../../core/ThemeContext";

import { flagMap } from "../../../core/FlagMap";

export default function LocalCountryCard({ country }) {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];
    const flagPath = flagMap[country.id.toLowerCase()] || require("../../../assets/flag-icons/us.png");

    function openWebsite() {
        if (country.website) {
            Linking.openURL(country.website);
        }
    }

    return (
        <View style={[styles.card, { backgroundColor: theme.card || theme.background }]}>
            <View style={styles.row}>
                <View style={{ flex: 1 }}>
                    <Text style={[styles.title, { color: theme.text }]}>{country.country}</Text>
                    <Text style={[styles.sub, { color: theme.textSecondary || theme.text }]}>
                        {country.demonym}
                    </Text>
                </View>

                <TouchableOpacity onPress={openWebsite}>
                    <Image source={flagPath} style={styles.flag} />
                </TouchableOpacity>
            </View>

            <View style={styles.info}>
                <Text style={[styles.item, { color: theme.text }]}>Population: {country.population}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Density: {country.density}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Area: {country.area}</Text>
                <Text style={[styles.item, { color: theme.text }]}>GDP: {country.gdp}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Median Age: {country.median_age}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Continent: {country.continent}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Currency: {country.currency}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Language: {country.language}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Calling Code: +{country.calling_code}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Driving Side: {country.driving_side}</Text>
                <Text style={[styles.item, { color: theme.text }]}>UN Member: {country.un_member}</Text>
                <Text style={[styles.item, { color: theme.text }]}>Religion: {country.religion}</Text>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    card: {
        padding: 16,
        borderRadius: 12,
        marginBottom: 12,
        elevation: 3,
    },
    row: {
        flexDirection: "row",
        alignItems: "center",
    },
    flag: {
        width: 48,
        height: 32,
        borderRadius: 4,
    },
    title: {
        fontSize: 20,
        fontWeight: "700",
    },
    sub: {
        fontSize: 14,
        opacity: 0.7,
    },
    info: {
        marginTop: 12,
    },
    item: {
        fontSize: 14,
        marginBottom: 4,
    },
});
