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
import { useColorScheme } from "react-native";
import { Colors } from "../../core/Colors";
import { useNavigation } from "@react-navigation/native";
import { useRouter, usePathname } from "expo-router";
import { useThemeMode } from "../../core/ThemeContext";

export default function Header() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];
    const router = useRouter();

    const [menuVisible, setMenuVisible] = useState(false);

    const menuIcon = activeTheme === 'dark' ? require("../../assets/icons/menu-dark.png") : require("../../assets/icons/menu-light.png");
    const accountIcon = activeTheme === 'dark' ? require("../../assets/icons/account-dark.png") : require("../../assets/icons/account-light.png");

    // Updated to handle names and routes
    const dropdownMenuItems = [
        { name: "Settings", route: "/settings" },
        { name: "About", route: "/about" },
        { name: "Contact", route: "/contact" },
        { name: "Terms & Conditions", route: "/terms" },
    ];

    return (
        <View style={[styles.headerContainer, { backgroundColor: theme.background }]}>
            <View style={[styles.searchBar, { backgroundColor: theme.highlighted_background || '#f0f0f0' }]}>

                <TouchableOpacity activeOpacity={1} onPress={() => setMenuVisible(true)} style={styles.iconButton}>
                    <Image source={menuIcon} style={styles.icon} />
                </TouchableOpacity>

                <TextInput
                    style={[styles.input, { color: theme.text }]}
                    placeholder="Search in wiki..."
                    placeholderTextColor="#888"
                    editable={false}
                />

                <TouchableOpacity activeOpacity={1} onPress={() => router.push("/work-in-progress")} style={styles.iconButton}>
                    <Image source={accountIcon} style={styles.icon} />
                </TouchableOpacity>
            </View>

            <Modal transparent={true} visible={menuVisible} animationType="fade" onRequestClose={() => setMenuVisible(false)}>
                <Pressable style={styles.modalOverlay} onPress={() => setMenuVisible(false)}>
                    <View style={[styles.dropdown, { backgroundColor: theme.background }]}>
                        {dropdownMenuItems.map((item, index) => (
                            <TouchableOpacity
                                key={index}
                                style={styles.menuItem}
                                onPress={() => {
                                    setMenuVisible(false);
                                    router.push(item.route); // Navigate to the specific route
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
        paddingTop: 60, // Adjust for status bar
        paddingHorizontal: 15,
        paddingBottom: 10,
    },
    searchBar: {
        flexDirection: "row",
        alignItems: "center",
        borderRadius: 25,
        paddingHorizontal: 10,
        height: 50,
        // Subtle shadow for the search bar
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
        top: 100, // Aligns roughly under the menu button
        left: 20,
        width: 180,
        borderRadius: 12,
        padding: 10,
        elevation: 5,
        shadowColor: "#000",
        shadowOffset: { width: 0, height: 4 },
        shadowOpacity: 0.3,
        shadowRadius: 5,
    },
    menuItem: {
        paddingVertical: 12,
        paddingHorizontal: 10,
    },
    menuText: {
        fontSize: 16,
        fontWeight: "500",
    },
});