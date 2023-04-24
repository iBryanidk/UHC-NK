package UHC;

import UHC.session.Session;
import UHC.session.SessionFactory;

import UHC.session.utils.Device;
import UHC.session.utils.DeviceModel;

import UHC.arena.GameHandler;
import UHC.arena.border.GameBorder;

import cn.nukkit.Player;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;

import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;

import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.player.PlayerMoveEvent;

public class MainListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        SessionFactory.getInstance().addSession(player.getName(), player.getUniqueId(), Device.fromId(player.getLoginChainData().getDeviceOS()), DeviceModel.fromId(player.getLoginChainData().getCurrentInputMode()));
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        SessionFactory.getInstance().removeSession(player.getName());
    }

    @EventHandler
    public void onPlayerChatEvent(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        Session session = SessionFactory.getInstance().getSession(player.getName());
        if(session.getTeam() != null){
            event.setFormat(session.isHost() ? TextFormat.colorize("&r&7[&l&4HOST&r&7]&r " + session.getTeam().getTeamColor() + " &r&7- &a" + player.getName() + "&r&f: " + message) : TextFormat.colorize(session.getTeam().getTeamColor() + " &r&7- &a" + player.getName() + "&r&f: " + message));
        }else{
            event.setFormat(session.isHost() ? TextFormat.colorize("&r&7[&l&4HOST&r&7]&r " + "&a" + player.getName() + "&r&f: " + message) : TextFormat.colorize("&a" + player.getName() + "&r&f: " + message));
        }
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if(!GameHandler.getInstance().isRunning()) return;

        if(from.distance(to) < GameBorder.getInstance().getRequiredPosition()) return;

        if(!GameBorder.getInstance().isBorderLimit(player.getPosition())){
            player.teleport(GameBorder.getInstance().getValidBorderPosition(player.getPosition()));
        }

        GameBorder.getInstance().buildWall(player);
    }
}


