package UHC.arena;

import UHC.session.Session;

import UHC.arena.utils.GameStatus;
import UHC.arena.utils.GamemodeType;
import static UHC.arena.utils.GameTime.*;

import cn.nukkit.level.Level;

import lombok.Getter;
import java.util.HashMap;

public class GameHandler {

    @Getter protected static GameHandler instance = new GameHandler();

    protected Level level = null;

    protected GamemodeType gamemodeType = GamemodeType.FFA;

    protected Session host = null;

    protected GameStatus status = GameStatus.WAITING;

    protected HashMap<String, String> scenarios = new HashMap<>();

    protected int startingTime = STARTING;
    protected int runningTime = RUNNING;
    protected int endingTime = ENDING;
    protected int preparingTime = PREPARING;

    public void prepareGame() {
        setStatus(GameStatus.PREPARING);
    }

    public void startGame() {
        setStatus(GameStatus.WAITING);
    }

    public void stopGame() {
        //do something
    }

    public Level getLevel() {
        return level;
    }

    public GamemodeType getGamemodeType() {
        return gamemodeType;
    }

    public void setGamemodeType(GamemodeType gamemodeType) {
        this.gamemodeType = gamemodeType;
    }

    public Session getHost() {
        return host;
    }

    public void setHost(Session host) {
        this.host = host;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Boolean isWaiting() {
        return getStatus() == GameStatus.WAITING;
    }

    public Boolean isPreparing() {
        return getStatus() == GameStatus.PREPARING;
    }

    public Boolean isStarting() {
        return getStatus() == GameStatus.STARTING;
    }

    public Boolean isRunning() {
        return getStatus() == GameStatus.RUNNING;
    }

    public Boolean isEnding() {
        return getStatus() == GameStatus.ENDING;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void addScenario(String scenario) {
        if(getScenario(scenario) != null) return;

        scenarios.put(scenario, scenario);
    }

    public void removeScenario(String scenario) {
        if(getScenario(scenario) == null) return;

        scenarios.remove(scenario);
    }

    public String getScenario(String scenario) {
        return scenarios.get(scenario);
    }

    public HashMap<String, String> getScenarios() {
        return scenarios;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void increaseStartingTime() {
        startingTime += 1;
    }

    public void decreaseStartingTime() {
        startingTime -= 1;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void increaseRunningTime() {
        runningTime += 1;
    }

    public void decreaseRunningTime() {
        runningTime -= 1;
    }

    public int getPreparingTime() {
        return preparingTime;
    }

    public void increasePreparingTime() {
        preparingTime += 1;
    }

    public void decreasePreparingTime() {
        preparingTime -= 1;
    }

    public int getEndingTime() {
        return endingTime;
    }

    public void increaseEndingTime() {
        endingTime += 1;
    }

    public void decreaseEndingTime() {
        endingTime -= 1;
    }
}