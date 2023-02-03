package UHC;

import cn.nukkit.command.Command;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

import lombok.Getter;

public class Loader extends PluginBase {

    @Getter protected static Loader instance = new Loader();

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        registerListener(
                new MainListener()
        );
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
