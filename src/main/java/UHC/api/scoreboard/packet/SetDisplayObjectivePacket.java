package UHC.api.scoreboard.packet;

import UHC.api.scoreboard.packet.data.DisplaySlot;
import UHC.api.scoreboard.packet.data.SortOrder;

import cn.nukkit.network.protocol.DataPacket;

public class SetDisplayObjectivePacket extends DataPacket {
    public static final byte NETWORK_ID = 107;

    private DisplaySlot displaySlot;
    private String objectiveId;
    private String displayName;
    private String criteria;
    private SortOrder sortOrder;

    public byte pid() {
        return NETWORK_ID;
    }

    public void decode() {

    }

    public void encode() {
        reset();

        putString(displaySlot.getName());
        putString(objectiveId);
        putString(displayName);
        putString(criteria);

        putVarInt(sortOrder.ordinal());
    }

    public void setDisplaySlot(DisplaySlot displaySlot) {
        this.displaySlot = displaySlot;
    }

    public void setObjectiveId(String objectiveId) {
        this.objectiveId = objectiveId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
}
