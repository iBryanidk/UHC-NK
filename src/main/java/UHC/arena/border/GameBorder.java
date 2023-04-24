package UHC.arena.border;

import UHC.utils.Builder;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

import lombok.Getter;

public class GameBorder {

    @Getter protected static GameBorder instance = new GameBorder();

    protected int border;
    protected int radius;
    protected float requiredPosition;

    public GameBorder() {
        this.border = 1000;
        this.radius = 4;
        this.requiredPosition = (float) Math.pow(0.10, 2);
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public float getRequiredPosition() {
        return requiredPosition;
    }

    public int getDistance(Position position1, Position position2) {
        return (int) position1.distance(position2);
    }

    public boolean isBorderLimit(Position position) {
        int border = getBorder();

        return position.getFloorX() >= -border && position.getFloorX() <= border && position.getFloorZ() >= -border && position.getFloorZ() <= border;
    }

    public Position getValidBorderPosition(Position position) {
        int border = getBorder();

        int x = position.getFloorX();
        int y = position.getFloorY();
        int z = position.getFloorZ();

        int xMin = -border;
        int zMin = -border;

        if(x <= xMin){
            x = xMin + 2;
        }else if(x >= border){
            x = border - 2;
        }
        if(z <= zMin){
            z = zMin + 2;
        }else if(z >= border){
            z = border - 2;
        }
        return new Position(x, y + 5, z, position.getLevel());
    }

    public void buildWall(Player player) {
        int radius = (this.radius - 3);

        Level level = player.getLevel();
        Position pos = player.getPosition().floor();

        Position cPos;
        for(int y = -radius; y <= radius + 5; y++){
            for(int x = -radius; x <= radius; x++){
                for(int z = -radius; z <= radius; z++){
                    if(this.isBorderLimit(cPos = Position.fromObject(pos.add(x, y, z), level))){
                        continue;
                    }
                    if(!level.getBlock(cPos).isTransparent()){
                        continue;
                    }
                    Builder.buildBlock(player, cPos);
                }
            }
        }
    }
}
