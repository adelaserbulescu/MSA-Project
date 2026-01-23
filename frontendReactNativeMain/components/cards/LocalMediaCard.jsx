import { View, Text, StyleSheet, Image, TouchableOpacity, Linking } from "react-native";
import { Colors } from "../../core/Colors";
import { useThemeMode } from "../../core/ThemeContext";
import { flagMap } from "../../core/FlagMap";
import { useRouter } from "expo-router";

export default function LocalMediaCard({ media }) {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];
    const router = useRouter();

    const flagPath =
        flagMap[media.countryTag?.toLowerCase()] ||
        require("../../assets/flag-icons/us.png");

    // -------------------------
    // ICONS (light/dark)
    // -------------------------
    const iconMap = {
        IMAGE: {
            light: require("../../assets/icons/media-image-light.png"),
            dark: require("../../assets/icons/media-image-dark.png"),
        },
        VIDEO: {
            light: require("../../assets/icons/media-video-light.png"),
            dark: require("../../assets/icons/media-video-dark.png"),
        },
        PAGE: {
            light: require("../../assets/icons/media-page-light.png"),
            dark: require("../../assets/icons/media-page-dark.png"),
        },
        ROUTE: {
            light: require("../../assets/icons/media-route-light.png"),
            dark: require("../../assets/icons/media-route-dark.png"),
        },
        QUIZ: {
            light: require("../../assets/icons/media-quiz-light.png"),
            dark: require("../../assets/icons/media-quiz-dark.png"),
        },
    };

    const icon = iconMap[media.mediaType]?.[activeTheme] ?? null;

    // -------------------------
    // THUMBNAIL LOGIC
    // -------------------------
    const getYouTubeThumbnail = (url) => {
        try {
            const id = url.split("/embed/")[1]?.split("?")[0];
            return id ? `https://img.youtube.com/vi/${id}/hqdefault.jpg` : null;
        } catch {
            return null;
        }
    };

    let thumbnail = null;

    if (media.mediaType === "IMAGE") {
        thumbnail = media.mediaPath;
    } else if (media.mediaType === "VIDEO") {
        thumbnail = getYouTubeThumbnail(media.mediaPath);
    }

    const rightImage = thumbnail ? { uri: thumbnail } : icon;

    // -------------------------
    // OPEN LINK OR ROUTE
    // -------------------------
    const handlePress = () => {
        if (media.mediaType === "ROUTE") {
            // Navigate inside the app
            router.push(media.mediaPath);
            return;
        }

        // External links (IMAGE, VIDEO, PAGE, QUIZ)
        if (media.mediaPath.startsWith("http")) {
            Linking.openURL(media.mediaPath);
        }
    };

    return (
        <TouchableOpacity
            style={[styles.card, { backgroundColor: theme.card_background }]}
            activeOpacity={0.8}
            onPress={handlePress}
        >
            {/* LEFT SIDE: FLAG */}
            <Image source={flagPath} style={styles.flag} />

            {/* MIDDLE: TEXT */}
            <View style={styles.textContainer}>
                <Text style={[styles.title, { color: Colors.accent2 }]}>
                    {media.mediaName}
                </Text>
                <Text style={[styles.description, { color: Colors.accent0 }]}>
                    {media.mediaDescription}
                </Text>
            </View>

            {/* RIGHT SIDE: ICON OR THUMBNAIL */}
            <Image source={rightImage} style={styles.thumbnail} />
        </TouchableOpacity>
    );
}

const styles = StyleSheet.create({
    card: {
        flexDirection: "row",
        alignItems: "center",
        padding: 12,
        borderRadius: 10,
        marginBottom: 12,
        // iOS Shadow Properties
        shadowColor: '#000',
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,

        // Android Shadow Property
        elevation: 5,
    },
    flag: {
        width: 40,
        height: 28,
        borderRadius: 4,
        marginRight: 12,
    },
    textContainer: {
        flex: 1,
        justifyContent: "center",
    },
    title: {
        fontSize: 16,
        fontWeight: "700",
    },
    description: {
        fontSize: 13,
        marginTop: 2,
    },
    thumbnail: {
        width: 60,
        height: 60,
        borderRadius: 6,
        marginLeft: 10,
    },
});
