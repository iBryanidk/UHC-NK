package UHC.arena.scatter;

import UHC.Loader;

import UHC.session.Session;
import UHC.session.SessionFactory;

import UHC.team.Team;
import UHC.team.TeamFactory;

import UHC.arena.GameHandler;
import UHC.arena.border.GameBorder;
import UHC.arena.utils.GamemodeType;

import UHC.event.player.GamePlayerJoinEvent;
import UHC.event.team.GameTeamJoinEvent;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.PluginException;

import lombok.Getter;
import javax.security.auth.login.LoginException;

import java.util.Collection;
import java.util.stream.Collectors;

public class GameScatter {

    @Getter protected static GameScatter instance = new GameScatter();

    public void startScattering() throws LoginException {
        Collection listeners = getListenersWaitingForScattering();

        if(listeners.size() < 1){
            Loader.getInstance().getLogger().warning("Failure initializing scattering. Scattering queue is empty!");

            return;
        }
        long start = System.currentTimeMillis();

        for(Object listener : listeners){
            if(listener instanceof Session){
                scatterPlayer((Session) listener, false);
            }else if(listener instanceof Team){
                scatterTeam((Team) listener, false);
            }
        }
        Loader.getInstance().getLogger().info("Scattering finished in " + (double) ((System.currentTimeMillis() - start) / 1000) + " secs.");
    }

    public void scatterPlayer(Session session, boolean force) {
        int border = GameBorder.getInstance().getBorder();
        Level level = GameHandler.getInstance().getLevel();
        GamemodeType gamemodeType = GameHandler.getInstance().getGamemodeType();

        if(gamemodeType != GamemodeType.FFA){
            throw new PluginException("Invalid gamemode " + gamemodeType.toString());
        }
        if(level == null){
            throw new PluginException("Can't perform scattering from " + session.getName() + " because world is null.");
        }
        if(session.isScattering() && !force){
            return;
        }
        int x = (int) (Math.random() * (border - (-border))) + (-border);
        int z = (int) (Math.random() * (border - (-border))) + (-border);
        if(!level.isChunkGenerated(x, z)){
            Loader.getInstance().getLogger().warning("Failure on scattering " + session.getName() + " to " + x + ":" + z + ", trying again ...");

            return;
        }
        int y = level.getHighestBlockAt(x, z) + 50;

        Position position = new Position(x, y, z, level);

        GamePlayerJoinEvent ev = new GamePlayerJoinEvent(session.getPlayerNonNull(), level, position);
        Loader.getInstance().getServer().getPluginManager().callEvent(ev);

        if(!ev.isCancelled()){
            session.setScattering(true);
            ev.getPlayer().teleport(ev.getTo());
        }
    }

    public void scatterTeam(Team team, boolean force) {
        int border = GameBorder.getInstance().getBorder();
        Level level = GameHandler.getInstance().getLevel();
        GamemodeType gamemodeType = GameHandler.getInstance().getGamemodeType();

        if(gamemodeType != GamemodeType.TEAMS){
            throw new PluginException("Invalid gamemode " + gamemodeType.toString());
        }
        if(level == null){
            throw new PluginException("Can't perform scattering from " + team.getId() + " because world is null.");
        }
        if(team.isScattering() && !force){
            return;
        }
        int x = (int) (Math.random() * (border - (-border))) + (-border);
        int z = (int) (Math.random() * (border - (-border))) + (-border);
        if(!level.isChunkGenerated(x, z)){
            Loader.getInstance().getLogger().warning("Failure on scattering " + team.getId() + " to " + x + ":" + z + ", trying again ...");

            return;
        }
        int y = level.getHighestBlockAt(x, z) + 50;

        Position position = new Position(x, y, z, level);

        GameTeamJoinEvent ev = new GameTeamJoinEvent(team.getOnlineMembers(), level, position);
        Loader.getInstance().getServer().getPluginManager().callEvent(ev);

        for(Session recipient : ev.getRecipients()){
            Player player;
            if(recipient.isHost() || (player = recipient.getPlayer()) == null){
                continue;
            }
            if(!ev.isCancelled()){
                recipient.setScattering(true);
                player.teleport(ev.getTo());
            }
        }
        team.setScattering(true);
    }

    protected Collection getListenersWaitingForScattering() throws LoginException {
        if(GameHandler.getInstance().getGamemodeType() == GamemodeType.FFA){

            return SessionFactory.getInstance().getSessions().values().stream().filter(session -> !session.isScattering()).collect(Collectors.toList());
        }else if(GameHandler.getInstance().getGamemodeType() == GamemodeType.TEAMS){

            return TeamFactory.getInstance().getTeams().values().stream().filter(team -> !team.isScattering()).collect(Collectors.toList());
        }else{

            throw new LoginException("Unexpected gamemode " + GameHandler.getInstance().getGamemodeType().toString());
        }
    }
}
