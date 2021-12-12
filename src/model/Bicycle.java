package model;

import java.util.Map;

/**
 *
 * @Author
 * @version
 */
public final class Bicycle extends AbstractVehicle{
    public Bicycle(int x, int y, Direction myDirection) {
        super(x,y,myDirection);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {

        return (!theTerrain.equals(Terrain.LIGHT) && !theTerrain.equals(Terrain.CROSSWALK)) || theLight.equals(Light.GREEN);
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};

        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.TRAIL)) {
                return testDirection;
            }
        }

        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.STREET) || theNeighbors.get(testDirection).equals(Terrain.LIGHT)
                    || theNeighbors.get(testDirection).equals(Terrain.CROSSWALK)) {
                return testDirection;
            }
        }

        return currentDirection.reverse();
    }

    @Override
    public int getDeathTime() {
        return 35;
    }
}
