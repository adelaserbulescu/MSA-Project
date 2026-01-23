import { useState, useEffect, useRef } from "react";
import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from "react-native";
import { Colors } from "../core/Colors";
import { useThemeMode } from "../core/ThemeContext";
import { API_URL } from "../core/ApiConfig";

import LocalMediaCard from "../components/cards/LocalMediaCard";
import localMedia from "../assets/data/local_media.json";

export default function Medias() {
    const { activeTheme } = useThemeMode();
    const theme = Colors[activeTheme];

    const scrollRef = useRef(null);

    // Local section toggle
    const [showLocal, setShowLocal] = useState(true);

    // Cloud section toggle
    const [showCloud, setShowCloud] = useState(true);

    // Cloud data
    const [cloudData, setCloudData] = useState([]);
    const [cloudError, setCloudError] = useState(null);

    // Fetch cloud media
    useEffect(() => {
        fetch(`${API_URL}/media`)
            .then(res => res.json())
            .then(json => {
                setCloudData(json.items ?? json ?? []); // supports both paginated & non-paginated
            })
            .catch(err => {
                setCloudError(err.message);
            });
    }, []);

    return (
        <ScrollView ref={scrollRef} style={{ backgroundColor: theme.background }}>
            <View style={styles.container}>

                {/* CLOUD MEDIA SECTION */}
                <TouchableOpacity onPress={() => setShowCloud(!showCloud)}>
                    <Text style={[styles.sectionTitle, { color: theme.text }]}>
                        Cloud Resources {showCloud ? "▼" : "▲"}
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
                                Loading cloud media...
                            </Text>
                        )}

                        {cloudData.map((media, index) => (
                            <LocalMediaCard
                                key={`cloud-media-${index}`}
                                media={{
                                    countryName: media.countryName ?? "Unknown",
                                    countryTag: media.countryTag ?? "xx",
                                    mediaName: media.mediaName ?? media.mediaType,
                                    mediaDescription: media.mediaDescription ?? "",
                                    mediaType: media.mediaType,
                                    mediaPath: media.mediaPath
                                }}
                            />
                        ))}
                    </>
                )}

                {/* LOCAL MEDIA SECTION */}
                <TouchableOpacity onPress={() => setShowLocal(!showLocal)}>
                    <Text style={[styles.sectionTitle, { color: theme.text }]}>
                        Archive Resources {showLocal ? "▼" : "▲"}
                    </Text>
                </TouchableOpacity>

                {showLocal && (
                    <>
                        {localMedia.map((media, index) => (
                            <LocalMediaCard
                                key={`local-media-${index}`}
                                media={media}
                            />
                        ))}
                    </>
                )}

            </View>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: { padding: 16 },
    sectionTitle: { fontSize: 22, fontWeight: "700", marginVertical: 12 },
});
