package UHC;

import UHC.api.scoreboard.Scoreboard;
import UHC.task.ScoreboardUpdaterTask;

import cn.nukkit.command.Command;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

import lombok.Getter;

public class Loader extends PluginBase {

    @Getter protected static Loader instance = new Loader();

    @Getter protected static Scoreboard scoreboard = new Scoreboard();

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        registerListener(
                new MainListener()
        );

        getServer().getScheduler().scheduleRepeatingTask(new ScoreboardUpdaterTask(), 20);
    }

    @Override
    public void onDisable() {

    }

    public void registerCommand(Command ...commands) {
        for (Command command: commands) {
            getServer().getCommandMap().register("uhc", command);
        }
    }
    
    public void registerListener(Listener ...listeners) {
        for (Listener listener: listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
