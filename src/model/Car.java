package model;

import java.util.Map;

public final class Car extends AbstractVehicle{

    public Car(int x, int y, Direction myDirection) {
        super(x, y, myDirection);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return (!theTerrain.equals(Terrain.LIGHT) || !theLight.equals(Light.RED)) && (!theTerrain.equals(Terrain.CROSSWALK)
                || theLight.equals(Light.GREEN));
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};

        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.STREET) || theNeighbors.get(testDirection).equals(Terrain.CROSSWALK)
                    || theNeighbors.get(testDirection).equals(Terrain.LIGHT)) {
                return testDirection;
            }
        }

        return currentDirection.reverse();
    }

    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     *
     * @return the number of updates.
     */
    @Override
    public int getDeathTime() {
        return 15;
    }


}
