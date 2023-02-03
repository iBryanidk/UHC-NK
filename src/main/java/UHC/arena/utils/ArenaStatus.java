package UHC.arena.utils;

import cn.nukkit.utils.TextFormat;

public enum ArenaStatus {
    WAITING(0),
    PREPARING(1),
    STARTING(2),
    RUNNING(3),
    ENDING(4);

    ArenaStatus(int i) {}

    public static String fromId(ArenaStatus id) {
        return switch(id){
            case WAITING -> TextFormat.colorize("&7Waiting");
            case PREPARING -> TextFormat.colorize("&ePreparing");
            case STARTING -> TextFormat.colorize("&aStarting");
            case RUNNING -> TextFormat.colorize("&aRunning");
            case ENDING -> TextFormat.colorize("&cEnding");
        };
    }
}
