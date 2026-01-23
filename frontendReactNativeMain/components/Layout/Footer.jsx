import { View, Text, TouchableOpacity, Image, StyleSheet, useColorScheme } from "react-native";
import { useRouter, usePathname } from "expo-router";
import { Colors } from "../../core/Colors";
import { useThemeMode } from "../../core/ThemeContext";


export default function Footer() {
    const router = useRouter();
    const pathname = usePathname();
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    const menuItems = [
        {
            name: "Wiki",
            route: "/wiki",
            iconLight: require("../../assets/icons/wiki-light.png"),
            iconDark: require("../../assets/icons/wiki-dark.png"),
        },
        {
            name: "Map",
            route: "/work-in-progress",
            iconLight: require("../../assets/icons/map-light.png"),
            iconDark: require("../../assets/icons/map-dark.png"),
        },
        {
            name: "Media",
            route: "/medias",
            iconLight: require("../../assets/icons/media-light.png"),
            iconDark: require("../../assets/icons/media-dark.png"),
        },
    ];

    return (
        <View style={[styles.container, { backgroundColor: theme.background }]}>
            {menuItems.map((item) => {
                const isActive = pathname === item.route;
                const icon = activeTheme === "dark" ? item.iconDark : item.iconLight;

                return (
                    <TouchableOpacity
                        key={item.route}
                        style={[
                            styles.button,
                            {
                                backgroundColor: isActive
                                    ? theme.highlighted_background
                                    : theme.background,
                            },
                        ]}
                        onPress={() => router.push(item.route)}
                    >
                        <Image source={icon} style={styles.icon} />

                        <Text
                            style={[
                                styles.label,
                                {
                                    color: isActive ? theme.highlighted_text : theme.text,
                                },
                            ]}
                        >
                            {item.name}
                        </Text>
                    </TouchableOpacity>
                );
            })}
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flexDirection: "row",
        justifyContent: "space-around",
        paddingVertical: 10,
        borderTopWidth: 1,
        borderColor: "#333",
    },
    button: {
        alignItems: "center",
        padding: 10,
        borderRadius: 12,
    },
    icon: {
        width: 28,
        height: 28,
        marginBottom: 4,
    },
    label: {
        fontSize: 12,
        fontWeight: "600",
    },
});
