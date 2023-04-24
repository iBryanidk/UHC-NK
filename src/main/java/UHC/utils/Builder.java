package UHC.utils;

import cn.nukkit.Player;
import cn.nukkit.level.Position;
import cn.nukkit.network.protocol.UpdateBlockPacket;

public class Builder {

    protected static int BEDROCK_RUNTIME_ID = 10879;
    protected static int DEFAULT_DATA_LAYER = 0;

    public static void buildBlock(Player player, Position position) {
        UpdateBlockPacket packet = new UpdateBlockPacket();

        packet.x = position.getFloorX();
        packet.y = position.getFloorY();
        packet.z = position.getFloorZ();

        packet.blockRuntimeId = BEDROCK_RUNTIME_ID;
        packet.flags = UpdateBlockPacket.FLAG_NETWORK;
        packet.dataLayer = DEFAULT_DATA_LAYER;

        player.getNetworkSession().sendPacket(packet);
    }
}
