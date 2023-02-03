package UHC.task;

import UHC.session.Session;
import UHC.session.SessionFactory;

import UHC.utils.ScoreboardBuilder;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.Task;

public class ScoreboardUpdaterTask extends Task {

    @Override
    public void onRun(int i){
        for(Player player : Server.getInstance().getOnlinePlayers().values()){
            if(!player.isOnline() || !player.spawned){
                continue;
            }
            Session session = SessionFactory.getInstance().getSession(player.getName());
            if(session == null){
                continue;
            }
            ScoreboardBuilder.build(session);
        }
    }
}
