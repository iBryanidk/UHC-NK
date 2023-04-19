package UHC.team.command;

import UHC.arena.GameHandler;
import UHC.arena.utils.GameStatus;
import UHC.arena.utils.GamemodeType;

import UHC.team.Team;
import UHC.team.TeamFactory;

import UHC.session.Session;
import UHC.session.SessionFactory;

import UHC.team.TeamMember;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.TextFormat;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class TeamCommand extends Command {

    public TeamCommand(String name, String description, String usageMessage, @Nullable String permission, String[] aliases) {
        super(name, description, usageMessage, aliases);

        this.setPermission(permission);
        this.setPermissionMessage("&cYou don't have permission.");

        this.commandParameters.clear();
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(TextFormat.colorize("&cRun this command in game."));
            return false;
        }
        if(GameHandler.getInstance().getGamemodeType() != GamemodeType.TEAMS){
            sender.sendMessage(TextFormat.colorize("&cCommand's available only in " + GamemodeType.TEAMS + " gamemode."));
            return false;
        }
        if(args.length == 0){
            sender.sendMessage(TextFormat.colorize("&c" + getUsage()));
            return false;
        }
        if(GameHandler.getInstance().getStatus() != GameStatus.WAITING){
            sender.sendMessage(TextFormat.colorize("&cOnly execute team commands in status: " + GameStatus.fromId(GameStatus.WAITING) + "&c."));
            return false;
        }

        Team team;
        String name;
        Player to;

        Session session = SessionFactory.getInstance().getSession(sender.getName());
        if(session == null){
            return false;
        }
        switch(args[0]){
            case "create" -> {
                team = session.getTeam();
                if(team != null){
                    sender.sendMessage(TextFormat.colorize("&cYou're already in " + team.getTeamColor() + "&c."));
                    return false;
                }
                int id = TeamFactory.getInstance().getTeams().size() + 1;

                TeamFactory.getInstance().createTeam(id, sender.getName(), ((Player) sender).getUniqueId());
                TeamFactory.getInstance().joinToTeam(session, TeamFactory.getInstance().getTeam(id));

                Server.getInstance().broadcastMessage(TextFormat.colorize(TeamFactory.getInstance().getTeam(id).getTeamColor() + "&f has been&a created" + "&f."));
            }
            case "disband" -> {
                team = session.getTeam();
                if(team == null){
                    sender.sendMessage(TextFormat.colorize("&cYou aren't in any team."));
                    return false;
                }
                if(team.getOwnerUUID() != ((Player) sender).getUniqueId()){
                    sender.sendMessage(TextFormat.colorize("&cOnly " + team.getTeamColor() + "&c's owner can disband team."));
                    return false;
                }
                TeamFactory.getInstance().deleteTeam(team.getId());

                Server.getInstance().broadcastMessage(TextFormat.colorize(team.getTeamColor() + "&f has been&c deleted" + "&f."));
            }
            case "leave" -> {
                team = session.getTeam();
                if(team == null){
                    sender.sendMessage(TextFormat.colorize("&cYou aren't in any team."));
                    return false;
                }
                if(team.getOwnerUUID() == ((Player) sender).getUniqueId()){
                    sender.sendMessage(TextFormat.colorize("&cYou're " + team.getTeamColor() + "&c's owner."));
                    return false;
                }
                team.broadcastMessage(TextFormat.colorize(sender.getName() + "&c left."));
                sender.sendMessage(TextFormat.colorize("&aYou've left " + team.getTeamColor() + "."));
            }
            case "invite" -> {
                team = session.getTeam();
                if(team == null){
                    sender.sendMessage(TextFormat.colorize("&cYou aren't in any team."));
                    return false;
                }
                if(team.getOwnerUUID() != ((Player) sender).getUniqueId()){
                    sender.sendMessage(TextFormat.colorize("&cOnly " + team.getTeamColor() + "&c's owner can invite players."));
                    return false;
                }
                if(args.length <= 1){
                    sender.sendMessage(TextFormat.colorize("&cTry: /team invite <name>"));
                    return false;
                }
                name = args[1];
                to = Server.getInstance().getPlayerExact(name);
                if(to == null){
                    sender.sendMessage(TextFormat.colorize("&c" + name + " is offline."));
                    return false;
                }
                SessionFactory.getInstance().getSession(to.getName()).setTeamInvite(team);

                sender.sendMessage(TextFormat.colorize("&aInvitation to " + to.getName() + " sent."));
                to.sendMessage(TextFormat.colorize("&aYou've been invited to " + team.getTeamColor() + "."));
            }
            case "accept" -> {
                team = session.getTeam();
                if(team != null){
                    sender.sendMessage(TextFormat.colorize("&cYou're already in " + team.getTeamColor() + "."));
                    return false;
                }
                Team teamInvite = session.getTeamInvite();
                if(teamInvite == null){
                    sender.sendMessage(TextFormat.colorize("&cNo one's invited you."));
                    return false;
                }
                if(teamInvite.getMembers().size() >= 3){
                    sender.sendMessage(TextFormat.colorize(teamInvite.getTeamColor() + "&c is full."));
                    return false;
                }
                TeamFactory.getInstance().joinToTeam(session, teamInvite);

                teamInvite.broadcastMessage(TextFormat.colorize(sender.getName() + "&a joined."));
                sender.sendMessage(TextFormat.colorize("&aYou've joined " + teamInvite.getTeamColor() + "."));
            }
            case "kick" -> {
                team = session.getTeam();
                if(team == null){
                    sender.sendMessage(TextFormat.colorize("&cYou're not in any Team."));
                    return false;
                }
                if(team.getOwnerUUID() != ((Player) sender).getUniqueId()){
                    sender.sendMessage(TextFormat.colorize("&cOnly the owner can kick a players."));
                    return false;
                }
                if(args.length <= 1){
                    sender.sendMessage(TextFormat.colorize("&cTry: /team kick <name>"));
                    return false;
                }
                name = args[1];
                to = Server.getInstance().getPlayerExact(name);
                if(to == null){
                    sender.sendMessage(TextFormat.colorize("&c" + name + " is offline."));
                    return false;
                }
                Session toSession = SessionFactory.getInstance().getSession(to.getName());
                if(toSession.getTeam() != null && !team.equals(toSession.getTeam())){
                    sender.sendMessage(TextFormat.colorize("&cPlayer team don't match."));
                    return false;
                }
                TeamFactory.getInstance().removeFromTeam(to.getName(), team);

                team.broadcastMessage(TextFormat.colorize(to.getName() + "&c kicked."));
                sender.sendMessage(TextFormat.colorize("&aYou've been &ekicked&a from " + team.getTeamColor() + "&a by &e" + sender.getName() + "."));
            }
            case "list" -> {
                ArrayList<String> ts = new ArrayList<>();
                ArrayList<String> m = new ArrayList<>();

                for (Team teamResult : TeamFactory.getInstance().getTeams().values()) {
                    for (TeamMember member : teamResult.getMembers().values()) {
                        m.add((member.isOnline() ? TextFormat.colorize("&r&a" + member.getName()) : TextFormat.colorize("&r&c" + member.getName())) + TextFormat.colorize(" &r&7(&e" + member.getDevice() + "&r&7)&r"));
                    }
                    ts.add(TextFormat.colorize(teamResult.getTeamColor() + "&7 ms&f: " + (String.join(", ", m))));
                }
                sender.sendMessage(String.join("\n", ts));
            }
            case "help", "?" -> {
                sender.sendMessage(TextFormat.colorize("&e/team create &7- Create new team"));
                sender.sendMessage(TextFormat.colorize("&e/team disband &7- Disbands a team"));
                sender.sendMessage(TextFormat.colorize("&e/team leave &7- Leave from a team"));
                sender.sendMessage(TextFormat.colorize("&e/team invite <name> &7- Invite players to your team"));
                sender.sendMessage(TextFormat.colorize("&e/team accept &7- Accept team invites"));
                sender.sendMessage(TextFormat.colorize("&e/team kick <name> &7- Kick players from your team"));
                sender.sendMessage(TextFormat.colorize("&e/team list &7- List all teams"));
            }
            default -> sender.sendMessage(TextFormat.colorize("&cUnrecognized command."));
        }
        return true;
    }
}
