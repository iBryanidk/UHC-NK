package UHC.utils;

import UHC.Loader;
import UHC.session.Session;

import UHC.arena.GameHandler;
import UHC.arena.utils.GameStatus;

import UHC.api.scoreboard.Scoreboard;

import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;

public class ScoreboardBuilder {

    public static void build(Session session) {
        ArrayList<String> lines = new ArrayList<>();

        GameHandler handler = GameHandler.getInstance();

        Scoreboard scoreboard = Loader.getScoreboard();

        scoreboard.setDisplayName(TextFormat.colorize("&r&l&dUHC"));
        scoreboard.removeLines();

        // FIRST SCOREBOARD LINE
        lines.add("&r&7--------------------- ");

        if(handler.getStatus() == GameStatus.WAITING){

            lines.add("&rStatus: " + (handler.getLevel() != null ? GameStatus.fromId(handler.getStatus()) : GameStatus.fromId(GameStatus.UNKNOWN)));

            if(handler.getScenarios().size() > 0){
                lines.add("&rScenarios: ");
                for(String scenario : handler.getScenarios().values()){
                    lines.add("&r- " + scenario);
                }
            }
        }else{
            switch(handler.getStatus()){
                case PREPARING:

                    lines.add("&rStatus: " + GameStatus.fromId(handler.getStatus()));
                    lines.add("&rPreparing ends in: " + Time.intToString(handler.getPreparingTime()));

                    lines.add(" ");

                    lines.add("&rScenarios: ");
                    if(handler.getScenarios().size() <= 0){
                        lines.add("&cNo scenarios set yet");
                    }else{
                        lines.add("&rScenarios: ");
                        for(String scenario : handler.getScenarios().values()){
                            lines.add("&r- " + scenario);
                        }
                    }
                break;
                case STARTING:

                    lines.add("&rStatus: " + GameStatus.fromId(handler.getStatus()));
                    lines.add("&rStarting in: " + Time.intToString(handler.getStartingTime()));

                    lines.add(" ");

                    lines.add("&rHosted by: " + (handler.getHost() != null ? handler.getHost().getName() : "Unknown"));
                    lines.add("&rGamemode: " + handler.getGamemodeType().toString());
                    lines.add("&rScenarios: ");
                    if(handler.getScenarios().size() <= 0){
                        lines.add("&cNo scenarios set yet");
                    }else{
                        lines.add("&rScenarios: ");
                        for(String scenario : handler.getScenarios().values()){
                            lines.add("&r- " + scenario);
                        }
                    }
                break;
                case RUNNING:
                    // TODO: PENDING RUNNING SCOREBOARD
                break;
                case ENDING:
                    // TODO: PENDING ENDING SCOREBOARD
                break;
            }
        }

        // SECOND SCOREBOARD LINE
        lines.add("&7&7--------------------- ");

        for(int i = 0; i < lines.size(); i++){
            scoreboard.setLine(i + 1, lines.get(i));
        }
        scoreboard.show(session.getPlayerNonNull());
    }
}
