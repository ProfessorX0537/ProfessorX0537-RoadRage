package model;

import java.util.Map;

/**
 * Class that contains specifics for Bicycle object
 *
 * @author Xavier Hines
 * @version 1.0
 */
public final class Bicycle extends AbstractVehicle {
    /**
     * Constructs a new Bicycle
     *
     * @param x Starting X-coordinate
     * @param y Starting Y-coordinate
     * @param myDirection Starting Direction
     */
    public Bicycle(int x, int y, Direction myDirection) {
        super(x,y,myDirection);
    }

    /**
     * Defines what Terrain the vehicle can pass through
     *
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return True or false depending on whether it can pass the terrain
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        //can pass through any terrain that isn't cross-walk or light that is not green.
        return (!theTerrain.equals(Terrain.LIGHT) && !theTerrain.equals(Terrain.CROSSWALK)) || theLight.equals(Light.GREEN);
    }

    /**
     * Logic that defines how the Bicycle decides which direction
     * it wants to travel.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return The direction that the Bicycle wants to travel.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};

        //First tests to see if there is a trail
        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.TRAIL)) {
                return testDirection;
            }
        }

        //Then tests direction for streets lights and crosswalks.
        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.STREET) || theNeighbors.get(testDirection).equals(Terrain.LIGHT)
                    || theNeighbors.get(testDirection).equals(Terrain.CROSSWALK)) {
                return testDirection;
            }
        }

        //If none of the options are available then turn around.
        return currentDirection.reverse();
    }

    /**
     * The amount of time that the vehicle will remain dead after
     * dying in a collision with another vehicle.
     *
     * @return The death time of Bicycle
     */
    @Override
    public int getDeathTime() {
        return 35;
    }
}
