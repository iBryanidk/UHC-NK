package UHC.session;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.PluginException;

public class Session {

    protected String name;
    protected String rawUUID;

    public Session(String name, String rawUUID) {
        this.name = name;
        this.rawUUID = rawUUID;
    }

    public String getName() {
        return name;
    }

    public String getRawUUID() {
        return rawUUID;
    }

    public Player getPlayer() {
        try {
            return getPlayerNonNull();
        } catch(PluginException exception){
            return null;
        }
    }

    public Player getPlayerNonNull() throws PluginException {
        Player result = Server.getInstance().getPlayer(getName());
        if(result == null){
            throw new PluginException("Player is offline");
        }
        return result;
    }
}
