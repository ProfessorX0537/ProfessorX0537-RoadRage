package model;

import java.util.Map;

/**
 *
 * @Author
 * @version
 */
public class Atv extends AbstractVehicle {

    public Atv(int x, int y, Direction myDirection) {
        super(x, y, myDirection);
    }


    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return true;
    }


    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction testDirection = Direction.random();

        while(theNeighbors.get(testDirection).equals(Terrain.WALL)) testDirection = Direction.random();

        return testDirection;
    }


    @Override
    public int getDeathTime() {
        return 25;
    }
}
