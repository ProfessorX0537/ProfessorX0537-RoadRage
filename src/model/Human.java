package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Methods pertaining specifically to the Human Object
 *
 * @author Xavier Hines
 * @version 1.0
 */
public final class Human extends AbstractVehicle {
    /**
     * Constructs a new Human
     *
     * @param x Starting X-coordinate
     * @param y Starting Y-coordinate
     * @param myDirection Starting Direction
     */
    public Human(int x, int y, Direction myDirection) {
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
        //the human can pass through terrain that isn't a crosswalk with a green light
        return !theTerrain.equals(Terrain.CROSSWALK) || (theLight.equals(Light.YELLOW) || theLight.equals(Light.RED));
    }

    /**
     * Logic that defines how the Human decides which direction
     * it wants to travel.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return The direction that the Human wants to travel.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};
        //shuffles array for random direction first
        Collections.shuffle(Arrays.asList(directions));

        //first detects if there is a crosswalk next to Human
        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.CROSSWALK)) return testDirection;
        }

        //Randomly picks a directions that the terrain is grass or trail
        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.GRASS) ||
                    theNeighbors.get(testDirection).equals(Terrain.TRAIL)) return testDirection;
        }

        return currentDirection.reverse();
    }

    /**
     * The amount of time that the vehicle will remain dead after
     * dying in a collision with another vehicle.
     *
     * @return The death time of Human
     */
    @Override
    public int getDeathTime() {
        return 45;
    }
}
