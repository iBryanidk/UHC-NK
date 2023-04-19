package UHC.arena.utils;

import java.util.Locale;

public enum GamemodeType {
    FFA("ffa"),
    TEAMS("teams");

    GamemodeType(String i) {}

    public String toString() {
        return name().toUpperCase(Locale.ROOT);
    }
}
