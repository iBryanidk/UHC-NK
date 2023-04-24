package UHC.command;

import UHC.session.Session;
import UHC.session.SessionFactory;

import UHC.arena.GameSetup;
import UHC.arena.GameHandler;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class UhcCommand extends Command {

    public UhcCommand(String name, String description, String usageMessage, String permission, String[] aliases) {
        super(name, description, usageMessage, aliases);

        this.setPermission(permission);
        this.setPermissionMessage("&cYou don't have permission.");

        this.commandParameters.clear();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(TextFormat.colorize("&cRun this command in game."));
            return false;
        }
        if(!sender.hasPermission(getPermission())){
            sender.sendMessage(TextFormat.colorize(getPermissionMessage()));
           return false;
        }
        if(args.length == 0){
            sender.sendMessage(TextFormat.colorize("&c" + getUsage()));
            return false;
        }
        Session session = SessionFactory.getInstance().getSession(sender.getName());
        if(session == null){
            return false;
        }
        switch(args[0]){
            case "prepare" -> {
                if(!session.isHost()){
                    sender.sendMessage(TextFormat.colorize("&cYou aren't host."));
                    return false;
                }
                if(!GameHandler.getInstance().isWaiting()){
                    sender.sendMessage(TextFormat.colorize("&cThe current state does not allow updating the game."));
                    return false;
                }
                GameHandler.getInstance().prepareGame();
            }
            case "start" -> {
                if(!session.isHost()){
                    sender.sendMessage(TextFormat.colorize("&cYou aren't host."));
                    return false;
                }
                if(!GameHandler.getInstance().isPreparing()){
                    sender.sendMessage(TextFormat.colorize("&cThe current state does not allow updating the game."));
                    return false;
                }
                GameHandler.getInstance().startGame();
            }
            case "stop" -> {
                if(!session.isHost()){
                    sender.sendMessage(TextFormat.colorize("&cYou aren't host."));
                    return false;
                }
                GameHandler.getInstance().stopGame();
            }
            case "setup" -> {
                if(!session.isHost()){
                    sender.sendMessage(TextFormat.colorize("&cYou aren't host."));
                    return false;
                }
                GameSetup.getInstance().sendSetupForm((Player) sender);
            }
            case "host" -> {
                if(!GameHandler.getInstance().isWaiting()){
                    sender.sendMessage(TextFormat.colorize("&cYou can't stop being the host until the UHC ends."));
                    return false;
                }
                for(Session s : SessionFactory.getInstance().getSessions().values()){
                    if(!s.isHost()){
                        continue;
                    }
                    if(s == GameHandler.getInstance().getHost() && s.getPlayerNonNull() != sender){
                        sender.sendMessage(TextFormat.colorize("&c" + s.getName() + " already hosting UHC."));
                        return false;
                    }
                }
                if(session.isHost()){
                    session.setHost(false);
                    sender.sendMessage(TextFormat.colorize("You're not &l&4HOST&r from &l&7UHC&r&f."));

                    GameHandler.getInstance().setHost(null);
                }else{
                    session.setHost(true);
                    sender.sendMessage(TextFormat.colorize("You're new &l&4HOST&r from &l&7UHC&r&f."));

                    GameHandler.getInstance().setHost(session);
                }
            }
            case "help", "?" -> {
                sender.sendMessage(TextFormat.colorize("&e/uhc prepare &7- Prepare a uhc"));
                sender.sendMessage(TextFormat.colorize("&e/uhc start &7- Start a uhc"));
                sender.sendMessage(TextFormat.colorize("&e/uhc stop &7- Stop a uhc"));
                sender.sendMessage(TextFormat.colorize("&e/uhc setup &7- Configure settings of uhc"));
                sender.sendMessage(TextFormat.colorize("&e/uhc host &7- Set host of the uhc"));
                sender.sendMessage(TextFormat.colorize("&e/uhc revive <name> &7- Revive a player"));
                sender.sendMessage(TextFormat.colorize("&e/uhc reviveteam <id> &7- Revive a team"));
            }
            default -> sender.sendMessage(TextFormat.colorize("&cUnrecognized command."));
        }
        return true;
    }
}
