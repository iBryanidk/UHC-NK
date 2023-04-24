package UHC;

import UHC.api.scoreboard.Scoreboard;
import UHC.task.ScoreboardUpdaterTask;

import UHC.command.UhcCommand;
import UHC.team.command.TeamCommand;

import UHC.utils.DefaultPermissionNames;

import UHC.utils.Time;
import cn.nukkit.command.Command;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

import cn.nukkit.utils.TextFormat;
import lombok.Getter;
import com.denzelcode.form.FormAPI;

import java.util.ArrayList;

public class Loader extends PluginBase {

    @Getter protected static Loader instance = new Loader();

    @Getter protected static Scoreboard scoreboard = new Scoreboard();

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

        FormAPI.init(this);

        registerListener(
                new MainListener()
        );
        registerCommand(
                new TeamCommand("team", "Team commands", "Try: /team help for more details about team commands.", null, new String[]{""}),
                new UhcCommand("uhc", "Uhc command for game management", "Try: /uhc help for more details about uhc commands.", DefaultPermissionNames.COMMAND_UHC, new String[]{""})
        );

        getServer().getScheduler().scheduleRepeatingTask(new ScoreboardUpdaterTask(), 20);
    }

    @Override
    public void onDisable() {

    }

    public void registerCommand(Command ...commands) {
        for(Command command: commands){
            getServer().getCommandMap().register("uhc", command);
        }
    }
    
    public void registerListener(Listener ...listeners) {
        for(Listener listener: listeners){
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
