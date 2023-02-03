package UHC.api.scoreboard.packet.data;

public class ScorerInfo {

    private final long scoreboardId;
    private final String objectiveId;

    private final int score;
    private final ScorerInfo.ScorerType type;
    private final String name;
    private final long entityId;

    public ScorerInfo(long scoreboardId, String objectiveId, int score) {
        this.scoreboardId = scoreboardId;
        this.objectiveId = objectiveId;
        this.score = score;
        this.type = ScorerInfo.ScorerType.INVALID;
        this.name = null;
        this.entityId = -1L;
    }

    public ScorerInfo(long scoreboardId, String objectiveId, int score, String name) {
        this.scoreboardId = scoreboardId;
        this.objectiveId = objectiveId;
        this.score = score;
        this.type = ScorerInfo.ScorerType.FAKE;
        this.name = name;
        this.entityId = -1L;
    }

    public ScorerInfo(long scoreboardId, String objectiveId, int score, ScorerInfo.ScorerType type, long entityId) {
        this.scoreboardId = scoreboardId;
        this.objectiveId = objectiveId;
        this.score = score;
        this.type = type;
        this.entityId = entityId;
        this.name = null;
    }

    public long getScoreboardId() {
        return this.scoreboardId;
    }

    public String getObjectiveId() {
        return this.objectiveId;
    }

    public int getScore() {
        return this.score;
    }

    public ScorerInfo.ScorerType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public long getEntityId() {
        return this.entityId;
    }

    public enum ScorerType {
        INVALID,
        PLAYER,
        ENTITY,
        FAKE;

        ScorerType() {}
    }
}
