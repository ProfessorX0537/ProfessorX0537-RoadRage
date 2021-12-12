package model;

import java.util.Map;

/**
 *
 * @Author
 * @version
 */
public class Taxi extends AbstractVehicle{

    private boolean wait = true;
    private static int count = 0;

    public Taxi(int x, int y, Direction myDirection) {
        super(x, y, myDirection);
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (!wait) {
            if (theTerrain.equals(Terrain.LIGHT) && theLight.equals(Light.RED)) return false;
            else if (theTerrain.equals(Terrain.CROSSWALK) && theLight.equals(Light.RED)) {
                wait = true;
                return false;
            }
            return theTerrain.equals(Terrain.STREET) || theTerrain.equals(Terrain.CROSSWALK)
                    || theTerrain.equals(Terrain.LIGHT);
        } else {
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
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
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
