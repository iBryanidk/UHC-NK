package UHC.arena;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;

import com.denzelcode.form.FormAPI;
import com.denzelcode.form.window.CustomWindowForm;
import com.denzelcode.form.window.SimpleWindowForm;

import lombok.Getter;
import java.util.Objects;

public class GameSetup {

    protected static String GAME_G = "game-setup";
    protected static String GAMEMODE_G = "gamemode-type";
    protected static String TEAMS_G = "teams-setup";
    protected static String SCENARIO_G = "scenario-types";
    protected static String BORDER_G = "border-setup";

    @Getter protected static GameSetup instance = new GameSetup();

    public void sendSetupForm(Player player) {
        SimpleWindowForm form = FormAPI.simpleWindowForm("UHC SETUP");

        form.addButton(GAME_G, TextFormat.colorize("&r&l&9GAME SETUP"));
        form.addButton(GAMEMODE_G, TextFormat.colorize("&r&l&9GAMEMODE TYPES SETUP"));
        form.addButton(TEAMS_G, TextFormat.colorize("&r&l&9TEAMS SETUP"));
        form.addButton(SCENARIO_G, TextFormat.colorize("&r&l&9SCENARIO TYPES SETUP"));
        form.addButton(BORDER_G, TextFormat.colorize("&r&l&9BORDER SETUP"));

        form.addHandler(SimpleFormButtonClickEvent -> {

            Player eventPlayer = SimpleFormButtonClickEvent.getPlayer();
            String buttonName = SimpleFormButtonClickEvent.getButton().getName();

            if(Objects.equals(buttonName, GAME_G)){
                sendGameSetupForm(eventPlayer);
            }else if(Objects.equals(buttonName, GAMEMODE_G)){
                sendGamemodeTypesSetupForm(eventPlayer);
            }else if(Objects.equals(buttonName, TEAMS_G)){
                sendTeamsSetupForm(eventPlayer);
            }else if(Objects.equals(buttonName, SCENARIO_G)){
                sendScenarioTypesSetupForm(eventPlayer);
            }else if(Objects.equals(buttonName, BORDER_G)){
                sendBorderSetupForm(eventPlayer);
            }else{
                eventPlayer.sendMessage(TextFormat.colorize("Unexpected button name: " + buttonName));
            }
        });

        form.sendTo(player);
    }

    protected void sendGameSetupForm(Player player) {
        CustomWindowForm form = FormAPI.customWindowForm(TextFormat.colorize("&r&l&9GAME SETUP"));

        form.sendTo(player);
    }

    protected void sendGamemodeTypesSetupForm(Player player) {
        CustomWindowForm form = FormAPI.customWindowForm(TextFormat.colorize("&r&l&9GAMEMODE TYPES SETUP"));

        form.sendTo(player);
    }

    protected void sendTeamsSetupForm(Player player) {
        CustomWindowForm form = FormAPI.customWindowForm(TextFormat.colorize("&r&l&9TEAMS SETUP"));

        form.sendTo(player);
    }

    protected void sendScenarioTypesSetupForm(Player player) {
        CustomWindowForm form = FormAPI.customWindowForm(TextFormat.colorize("&r&l&9SCENARIO TYPES SETUP"));

        form.sendTo(player);
    }

    protected void sendBorderSetupForm(Player player) {
        CustomWindowForm form = FormAPI.customWindowForm(TextFormat.colorize("&r&l&9BORDER SETUP"));

        form.sendTo(player);
    }
}
