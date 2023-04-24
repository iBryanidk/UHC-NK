package UHC.event.team;

import UHC.session.Session;

import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

import cn.nukkit.event.Event;
import cn.nukkit.event.Cancellable;

import java.util.ArrayList;

public class GameTeamJoinEvent extends Event implements Cancellable {

    protected ArrayList<Session> recipients;
    protected Level level;
    protected Position to;

    public GameTeamJoinEvent(ArrayList<Session> recipients, Level level, Position to){
        this.recipients = recipients;
        this.level = level;
        this.to = to;
    }

    public ArrayList<Session> getRecipients() {
        return recipients;
    }

    public Level getLevel() {
        return level;
    }

    public Position getTo() {
        return to;
    }
}