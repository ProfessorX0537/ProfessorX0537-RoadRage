package model;

import java.util.Map;

/**
 * Methods pertaining specifically to the Car Object
 *
 * @author Xavier Hines
 * @version 1.0
 */
public final class Car extends AbstractVehicle{
    /**
     * Constructs a new Car
     *
     * @param x Starting X-coordinate
     * @param y Starting Y-coordinate
     * @param myDirection Starting Direction
     */
    public Car(int x, int y, Direction myDirection) {
        super(x, y, myDirection);
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
        //Can pass through terrain that isn't a yellow or red crosswalk or red light
        return (!theTerrain.equals(Terrain.LIGHT) || !theLight.equals(Light.RED)) && (!theTerrain.equals(Terrain.CROSSWALK)
                || theLight.equals(Light.GREEN));
    }

    /**
     * Logic that defines how the Car decides which direction
     * it wants to travel.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return The direction that the Car wants to travel.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};

        //Stays on streets and goes straight, left, or right.
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
     * @return The death time of Car
     */
    @Override
    public int getDeathTime() {
        return 15;
    }


}
