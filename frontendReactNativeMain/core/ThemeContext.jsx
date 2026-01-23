import { createContext, useContext, useState, useEffect } from "react";
import { useColorScheme } from "react-native";
const ThemeContext = createContext();

export function ThemeProvider({ children }) {
    const systemScheme = useColorScheme();
    const [override, setOverride] = useState("system"); // system | light | dark

    const activeTheme =
        override === "system" ? systemScheme : override;

    return (
        <ThemeContext.Provider value={{ activeTheme, override, setOverride }}>
            {children}
        </ThemeContext.Provider>
    );
}

export function useThemeMode() {
    return useContext(ThemeContext);
}


