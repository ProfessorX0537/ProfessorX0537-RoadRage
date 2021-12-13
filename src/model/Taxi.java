package model;

import java.util.Map;

/**
 * Methods pertaining specifically to the Taxi Object
 *
 * @author Xavier Hines
 * @version 1.0
 */
public class Taxi extends AbstractVehicle{

    /**
     * boolean flag to make taxi wait at red crosswalk for a period of time
     */
    private static boolean wait = true;
    /**
     * keep track of number of steps taxi has waited
     */
    private static int count = 0;

    /**
     * Constructs a new Taxi
     *
     * @param x Starting X-coordinate
     * @param y Starting Y-coordinate
     * @param myDirection Starting Direction
     */
    public Taxi(int x, int y, Direction myDirection) {
        super(x, y, myDirection);
    }


    /**
     * Defines what Terrain the vehicle can pass through
     *
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return True or false depending on whether it can pass onto the terrain
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (!wait) {
            //can't pass through red lights
            if (theTerrain.equals(Terrain.LIGHT) && theLight.equals(Light.RED)) return false;
            //will pass through red lights on crosswalk if waiting more than three steps
            else if (theTerrain.equals(Terrain.CROSSWALK) && theLight.equals(Light.RED)) {
                wait = true;
                return false;
            }
            //else can pass if the terrain is a street crosswalk or light
            return theTerrain.equals(Terrain.STREET) || theTerrain.equals(Terrain.CROSSWALK)
                    || theTerrain.equals(Terrain.LIGHT);
        } else {
            //used to keep track of number of steps so that taxi can pass
            //through red crosswalk after 3 steps
            count++;
            if (count == 4 || theLight.equals(Light.GREEN)) {
                count = 0;
                wait = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Logic that defines how the Taxi decides which direction
     * it wants to travel.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return The direction that the Taxi wants to travel.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};

        //Prefers to go straight then, left, finally right.
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
     * @return The death time of Taxi
     */
    @Override
    public int getDeathTime() {
        return 15;
    }
}
