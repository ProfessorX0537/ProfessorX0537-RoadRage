package model;

import java.util.*;

/**
 * Methods pertaining specifically to the Truck Object
 *
 * @author Xavier Hines
 * @version 1.0
 */
public final class Truck extends AbstractVehicle {

    /**
     * Constructs a new Truck
     *
     * @param x Starting X-coordinate
     * @param y Starting Y-coordinate
     * @param myDirection Starting Direction
     */
    public Truck(int x, int y, Direction myDirection) {
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
        //only stops for red crosswalks
        return !theTerrain.equals(Terrain.CROSSWALK) || !theLight.equals(Light.RED);
    }

    /**
     * Logic that defines how the Truck decides which direction
     * it wants to travel.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return The direction that the Truck wants to travel.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};
        //shuffles array so direction is chosen at random.
        Collections.shuffle(Arrays.asList(directions));

        //chooses direction at random
        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.STREET) || theNeighbors.get(testDirection).equals(Terrain.CROSSWALK)
                    || theNeighbors.get(testDirection).equals(Terrain.LIGHT)) {
                return testDirection;
            }
        }

        return currentDirection.reverse();
    }

    /**
     * The amount of time that the vehicle will remain dead after
     * dying in a collision with another vehicle.
     *
     * @return The death time of Truck
     */
    @Override
    public int getDeathTime() {
        return 0;
    }
}
