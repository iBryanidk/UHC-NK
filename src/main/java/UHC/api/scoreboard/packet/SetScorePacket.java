package UHC.api.scoreboard.packet;

import UHC.api.scoreboard.packet.data.ScorerInfo;
import cn.nukkit.network.protocol.DataPacket;

import java.util.List;
import java.util.ArrayList;

public class SetScorePacket extends DataPacket {
    public static final byte NETWORK_ID = 108;

    private final SetScorePacket.Action action;
    private final List<ScorerInfo> infos = new ArrayList();

    public byte pid() {
        return NETWORK_ID;
    }

    public void decode() {

    }

    public void encode() {
        reset();

        putByte((byte)action.ordinal());
        putUnsignedVarInt(infos.size());

        for(ScorerInfo info : infos){

            putVarLong(info.getScoreboardId());
            putString(info.getObjectiveId());
            putLInt(info.getScore());

            if(action == SetScorePacket.Action.SET){

                putByte((byte)info.getType().ordinal());

                switch(info.getType()){
                    case ENTITY, PLAYER -> putUnsignedVarLong(info.getEntityId());
                    case FAKE -> putString(info.getName());
                    default -> throw new IllegalArgumentException("Invalid score type received");
                }
            }
        }

    }

    public SetScorePacket(SetScorePacket.Action action) {
        this.action = action;
    }

    public List<ScorerInfo> getInfos() {
        return infos;
    }

    public enum Action {
        SET,
        REMOVE;

        Action() {}
    }
}
