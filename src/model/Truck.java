package model;

import java.util.*;

/**
 *
 * @Author
 * @version
 */
public class Truck extends AbstractVehicle {

    public Truck(int x, int y, Direction myDirection) {
        super(x,y,myDirection);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return !theTerrain.equals(Terrain.CROSSWALK) || !theLight.equals(Light.RED);
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};
        Collections.shuffle(Arrays.asList(directions));

        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.STREET) || theNeighbors.get(testDirection).equals(Terrain.CROSSWALK)
                    || theNeighbors.get(testDirection).equals(Terrain.LIGHT)) {
                return testDirection;
            }
        }

        return currentDirection.reverse();
    }

    @Override
    public int getDeathTime() {
        //truck can't die
        return 0;
    }
}
