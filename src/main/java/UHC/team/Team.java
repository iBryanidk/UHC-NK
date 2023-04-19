package UHC.team;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.PluginException;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Team {

    protected Integer id;
    protected String owner;
    protected UUID ownerUUID;

    protected Boolean scattering = false;

    protected HashMap<String, TeamMember> members = new HashMap<>();

    public Team(Integer id, String owner, UUID ownerUUID) {
        this.id = id;
        this.owner = owner;
        this.ownerUUID = ownerUUID;
    }

    public Integer getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public Player getOwnerNonNull() throws PluginException {
        Player result = Server.getInstance().getPlayerExact(getOwner());
        if(result == null){
            throw new PluginException("Player is offline");
        }
        return result;
    }

    public Boolean isScattering() {
        return scattering;
    }

    public void setScattering(Boolean scattering) {
        this.scattering = scattering;
    }

    public void addMember(TeamMember teamMember) {
        if(getMember(teamMember.getName()) != null) return;

        members.put(teamMember.getName(), teamMember);
    }

    public void removeMember(String name) {
        if(getMember(name) == null) return;

        members.remove(name);
    }

    public TeamMember getMember(String name) {
        return members.get(name);
    }

    public HashMap<String, TeamMember> getMembers() {
        return members;
    }

    public ArrayList<Player> getOnlineMembers() {
        ArrayList<Player> players = new ArrayList<>();
        for(TeamMember teamMember : getMembers().values()){
            Player player = Server.getInstance().getPlayerExact(teamMember.getName());
            if(player == null){
                continue;
            }
            players.add(player);
        }
        return players;
    }

    public void broadcastMessage(String m) {
        for(Player player : getOnlineMembers()){
            player.sendMessage(m);
        }
    }

    public String getTeamColor() {
        return TextFormat.colorize("&eTeam " + getId() + "&r");
    }

    public Boolean equals(Team team) {
        return Objects.equals(getId(), team.getId());
    }
}
