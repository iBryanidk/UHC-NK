package UHC.team;

import UHC.session.Session;
import UHC.session.SessionFactory;
import lombok.Getter;

import java.util.UUID;
import java.util.HashMap;

public class TeamFactory {

    @Getter protected static TeamFactory instance = new TeamFactory();

    protected HashMap<Integer, Team> teams = new HashMap<>();

    public void createTeam(int id, String owner, UUID ownerUUID) {
        teams.put(id, new Team(id, owner, ownerUUID));
    }

    public void deleteTeam(int id) {
        Team team = getTeam(id);
        if(team != null){
            for(TeamMember teamMember : team.getMembers().values()){
                removeFromTeam(teamMember.getName(), team);
            }
        }
        teams.remove(id);
    }

    public Team getTeam(int id) {
        return teams.get(id);
    }

    public HashMap<Integer, Team> getTeams() {
        return teams;
    }

    public void joinToTeam(Session session, Team team) {
        session.setTeam(team);

        team.addMember(new TeamMember(session.getName(), session.getDevice(), session.getDeviceModel()));
    }

    public void removeFromTeam(String name, Team team) {
        Session session = SessionFactory.getInstance().getSession(name);
        if(session != null){
            session.setTeam(null);
        }
        team.removeMember(name);
    }

    public Boolean equalsExact(Session s1, Session s2) {
        Team t1 = s1.getTeam();
        Team t2 = s2.getTeam();
        return t1 != null && t2 != null && t1.equals(t2);
    }
}