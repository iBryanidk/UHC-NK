package UHC.session;

import cn.nukkit.Player;
import lombok.Getter;
import java.util.HashMap;
import java.util.UUID;

public class SessionFactory {

    @Getter protected static SessionFactory instance = new SessionFactory();

    protected HashMap<String, Session> sessions = new HashMap<>();

    public void addSession(String name, UUID rawUUID, String device, String deviceModel) {
        sessions.put(name, new Session(name, rawUUID, device, deviceModel));
    }

    public void removeSession(String name) {
        sessions.remove(name);
    }

    public Session getSession(String name) {
        return sessions.get(name);
    }

    public HashMap<String, Session> getSessions() {
        return sessions;
    }

    public Player getPlayerSession(String name) {
        return getSession(name).getPlayerNonNull();
    }
}
