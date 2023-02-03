package UHC.utils;

import UHC.Loader;
import UHC.session.Session;
import UHC.api.scoreboard.Scoreboard;

import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;

public class ScoreboardBuilder {

    public static void build(Session session) {
        ArrayList<String> lines = new ArrayList<>();

        Scoreboard scoreboard = Loader.getScoreboard();

        scoreboard.setDisplayName(TextFormat.colorize("&r&l&dUHC"));

        lines.add(TextFormat.colorize("&r&7 --------------------- "));
        lines.add(TextFormat.colorize("&r  Status: " + "&4Unavailable game "));
        lines.add(TextFormat.colorize("&7&7 --------------------- "));

        for(int i = 0; i < lines.size(); i++){
            scoreboard.setLine(i + 1, lines.get(i));
        }
        scoreboard.show(session.getPlayerNonNull());
    }
}
