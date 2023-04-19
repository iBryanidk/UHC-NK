package UHC.session;

import UHC.team.Team;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.PluginException;

import java.util.Optional;
import java.util.UUID;

public class Session {

    protected String name;
    protected UUID UUID;

    protected Boolean spectator = false;
    protected Boolean host = false;
    protected Boolean scattering = false;

    protected Team team = null;
    protected Team teamInvite = null;

    protected String device;
    protected String deviceModel;

    public Session(String name, UUID UUID, String device, String deviceModel) {
        this.name = name;
        this.UUID = UUID;
        this.device = device;
        this.deviceModel = deviceModel;
    }

    public String getName() {
        return name;
    }

    public UUID getRawUUID() {
        return UUID;
    }

    public String getDevice() {
        return device;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public Boolean isSpectator() {
        return spectator && getPlayerNonNull().isSpectator();
    }

    public void setSpectator(Boolean spectator) {
        this.spectator = spectator;

        getPlayerNonNull().setGamemode(spectator ? 3 : 0);
    }

    public Boolean isHost() {
        return host;
    }

    public void setHost(Boolean host) {
        this.host = host;
    }

    public Boolean isScattering() {
        return scattering;
    }

    public void setScattering(Boolean scattering) {
        this.scattering = scattering;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeamInvite() {
        return teamInvite;
    }

    public void setTeamInvite(Team teamInvite) {
        this.teamInvite = teamInvite;
    }

    public Player getPlayer() {
        try {
            return getPlayerNonNull();
        } catch(PluginException exception){
            return null;
        }
    }

    public Player getPlayerNonNull() throws PluginException {
        Optional<Player> result = Server.getInstance().getPlayer(getRawUUID());
        if(result.isEmpty()){
            throw new PluginException("Player is offline");
        }
        return result.get();
    }
}
