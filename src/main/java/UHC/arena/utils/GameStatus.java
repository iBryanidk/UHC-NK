package UHC.arena.utils;

import cn.nukkit.utils.TextFormat;

public enum GameStatus {
    PREPARING(0),
    WAITING(1),
    STARTING(2),
    RUNNING(3),
    ENDING(4),
    UNKNOWN(-1);

    GameStatus(int i) {}

    public static String fromId(GameStatus id) {
        return switch(id){
            case PREPARING -> TextFormat.colorize("&ePreparing&r");
            case WAITING -> TextFormat.colorize("&7Waiting&r");
            case STARTING -> TextFormat.colorize("&aStarting&r");
            case RUNNING -> TextFormat.colorize("&aRunning&r");
            case ENDING -> TextFormat.colorize("&cEnding&r");
            default -> TextFormat.colorize("&4Unavailable game&r");
        };
    }
}
