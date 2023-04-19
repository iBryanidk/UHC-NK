package UHC.team;

import UHC.session.Session;
import UHC.session.SessionFactory;

import cn.nukkit.Player;
import cn.nukkit.Server;

public class TeamMember {

    protected String name;
    protected String device;
    protected String deviceModel;

    public TeamMember(String name, String device, String deviceModel){
        this.name = name;
        this.device = device;
        this.deviceModel = deviceModel;
    }

    public String getName() {
        return name;
    }

    public String getDevice() {
        return device;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public Boolean isOnline() {
        Player player = Server.getInstance().getPlayerExact(getName());
        if(player == null){
            return false;
        }
        Session session = SessionFactory.getInstance().getSession(getName());
        return session != null && !session.isSpectator();
    }
}
