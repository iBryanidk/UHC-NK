package UHC.arena;

import lombok.Getter;

public class GameSettings {

    @Getter protected static GameSetup instance = new GameSetup();

    protected int max_team_players = 2;
    protected int max_keyboard_players_per_team = 1;
    protected int apple_rate = 5;

    public int getMaxTeamPlayers() {
        return max_team_players;
    }

    public void setMaxTeamPlayers(int max_team_players) {
        this.max_team_players = max_team_players;
    }

    public int getMaxKeyboardPlayers() {
        return max_keyboard_players_per_team;
    }

    public void setMaxKeyboardPlayers(int max_keyboard_players_per_team) {
        this.max_keyboard_players_per_team = max_keyboard_players_per_team;
    }

    public int getAppleRate() {
        return apple_rate;
    }

    public void setAppleRate(int apple_rate) {
        this.apple_rate = apple_rate;
    }
}
