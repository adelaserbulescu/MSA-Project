import React, { useState } from "react";
import {
    View,
    TextInput,
    TouchableOpacity,
    Image,
    StyleSheet,
    Text,
    Modal,
    Pressable
} from "react-native";
import { Colors } from "../../core/Colors";
import { useRouter } from "expo-router";
import { useThemeMode } from "../../core/ThemeContext";

export default function Header() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];
    const router = useRouter();

    const [menuVisible, setMenuVisible] = useState(false);
    const [search, setSearch] = useState("");

    // MULTI-SELECT SEARCH FIELDS
    const [selectedFields, setSelectedFields] = useState([
        "country",
        "tag",
        "groupid",
        "continent",
        "currency",
        "language",
    ]);

    const toggleField = (field) => {
        setSelectedFields((prev) =>
            prev.includes(field)
                ? prev.filter((f) => f !== field)
                : [...prev, field]
        );
    };

    const menuIcon = activeTheme === 'dark'
        ? require("../../assets/icons/menu-dark.png")
        : require("../../assets/icons/menu-light.png");

    const accountIcon = activeTheme === 'dark'
        ? require("../../assets/icons/account-dark.png")
        : require("../../assets/icons/account-light.png");

    const dropdownMenuItems = [
        { name: "Settings", route: "/settings" },
        { name: "About", route: "/about" },
        { name: "Contact", route: "/contact" },
        { name: "Terms & Conditions", route: "/terms" },
    ];

    return (
        <View style={[styles.headerContainer, { backgroundColor: theme.background }]}>
            <View style={[styles.searchBar, { backgroundColor: theme.highlighted_background }]}>

                <TouchableOpacity onPress={() => setMenuVisible(true)} style={styles.iconButton}>
                    <Image source={menuIcon} style={styles.icon} />
                </TouchableOpacity>

                <TextInput
                    style={[styles.input, { color: theme.text }]}
                    placeholder="Search in wiki..."
                    placeholderTextColor="#888"
                    value={search}
                    onChangeText={setSearch}
                    onSubmitEditing={() => {
                        router.push(
                            `/wiki?search=${encodeURIComponent(search)}&fields=${encodeURIComponent(selectedFields.join(","))}`
                        );
                    }}
                />

                <TouchableOpacity onPress={() => router.push("/work-in-progress")} style={styles.iconButton}>
                    <Image source={accountIcon} style={styles.icon} />
                </TouchableOpacity>
            </View>

            {/* DROPDOWN MENU */}
            <Modal transparent visible={menuVisible} animationType="fade">
                <Pressable style={styles.modalOverlay} onPress={() => setMenuVisible(false)}>
                    <View style={[styles.dropdown, { backgroundColor: theme.background }]}>

                        {/* SEARCH FIELD FILTERS */}
                        <Text style={[styles.sectionLabel, { color: theme.text }]}>Search Fields</Text>

                        {[
                            "country",
                            "tag",
                            "groupid",
                            "continent",
                            "currency",
                            "language",
                        ].map((field) => (
                            <TouchableOpacity
                                key={field}
                                style={styles.menuItem}
                                onPress={() => toggleField(field)}
                            >
                                <Text style={[styles.menuText, { color: theme.text }]}>
                                    {selectedFields.includes(field) ? "☑ " : "☐ "}
                                    {field}
                                </Text>
                            </TouchableOpacity>
                        ))}

                        {/* THIN SEPARATOR */}
                        <View style={[styles.separator, { borderColor: theme.text }]} />

                        {/* NAVIGATION MENU */}
                        {dropdownMenuItems.map((item, index) => (
                            <TouchableOpacity
                                key={index}
                                style={styles.menuItem}
                                onPress={() => {
                                    setMenuVisible(false);
                                    router.push(item.route);
                                }}
                            >
                                <Text style={[styles.menuText, { color: theme.text }]}>{item.name}</Text>
                            </TouchableOpacity>
                        ))}
                    </View>
                </Pressable>
            </Modal>
        </View>
    );
}

const styles = StyleSheet.create({
    headerContainer: {
        paddingTop: 60,
        paddingHorizontal: 15,
        paddingBottom: 10,
    },
    searchBar: {
        flexDirection: "row",
        alignItems: "center",
        borderRadius: 25,
        paddingHorizontal: 10,
        height: 50,
        shadowColor: "#000",
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.1,
        shadowRadius: 4,
        elevation: 3,
    },
    iconButton: {
        padding: 8,
    },
    icon: {
        width: 24,
        height: 24,
        resizeMode: "contain",
    },
    input: {
        flex: 1,
        fontSize: 16,
        marginHorizontal: 10,
    },
    modalOverlay: {
        flex: 1,
        backgroundColor: "rgba(0,0,0,0.2)",
    },
    dropdown: {
        position: "absolute",
        top: 100,
        left: 20,
        width: 220,
        borderRadius: 12,
        padding: 10,
        elevation: 5,
    },
    sectionLabel: {
        fontSize: 14,
        fontWeight: "700",
        marginBottom: 6,
        opacity: 0.7,
    },
    separator: {
        borderBottomWidth: 1,
        marginVertical: 10,
        opacity: 0.3,
    },
    menuItem: {
        paddingVertical: 10,
    },
    menuText: {
        fontSize: 16,
        fontWeight: "500",
    },
});
