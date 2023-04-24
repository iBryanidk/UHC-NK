package UHC.event.player;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.player.PlayerEvent;

public class GamePlayerJoinEvent extends PlayerEvent implements Cancellable {

    protected Level level;
    protected Position to;

    public GamePlayerJoinEvent(Player player, Level level, Position to){
        this.player = player;
        this.level = level;
        this.to = to;
    }

    public Level getLevel() {
        return level;
    }

    public Position getTo() {
        return to;
    }
}