package model;

import java.util.Map;

/**
 * Class to make an ATV object
 *
 * @author Xavier Hines
 * @version 1.0
 */
public class Atv extends AbstractVehicle {

    /**
     * Constructs a new ATV
     *
     * @param x Starting X-coordinate
     * @param y Starting Y-coordinate
     * @param myDirection Starting Direction
     */
    public Atv(int x, int y, Direction myDirection) {
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
        //can pass through all terrain
        return !theTerrain.equals(Terrain.WALL);
    }

    /**
     * Logic that defines how the ATV decides which direction
     * it wants to travel.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return The direction that the ATV wants to travel.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction testDirection = Direction.random();

        while(theNeighbors.get(testDirection).equals(Terrain.WALL)) testDirection = Direction.random();

        return testDirection;
    }

    /**
     * The amount of time that the vehicle will remain dead after
     * dying in a collision with another vehicle.
     *
     * @return The death time of ATV
     */
    @Override
    public int getDeathTime() {
        return 25;
    }
}
