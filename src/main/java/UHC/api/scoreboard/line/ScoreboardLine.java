package UHC.api.scoreboard.line;

import UHC.api.scoreboard.Scoreboard;

public class ScoreboardLine {

    private final String text;
    private final Scoreboard scoreboard;

    public ScoreboardLine(Scoreboard scoreboard, String text) {
        this.scoreboard = scoreboard;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
