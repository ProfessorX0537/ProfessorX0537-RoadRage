package model;
/**
 * Abstract vehicle holds all implemented methods that will be shared amongst
 * the Concrete child vehicle classes.
 *
 * @author Xavier Hines
 * @version 1.0
 */
public abstract class AbstractVehicle implements Vehicle {

    /**
     * Keeps track of the number of times the vehicle is poked when dead.
     */
    private  int poked = 0;

    /**
     * Maintains whether the vehicle is dead.
     */
    private  boolean deadOrAlive = true;

    /**
     * The starting X value of the vehicle so that X position can be
     * reset correctly.
     */
    private final int initialX;

    /**
     * The starting Y value of the vehicle so that Y position can be
     * reset correctly.
     */
    private final int initialY;

    /**
     * Maintains the current direction in which the vehicle is wanting to move.
     */
    protected Direction currentDirection;

    /**
     * Maintains the current X position of the vehicle on the map.
     */
    private int currentX;

    /**
     * Maintains the current Y position of the vehicle on the map.
     */
    private int currentY;


    /**
     * Constructor for this class so that values from children classes can be passed up
     * to this parent class.
     *
     * @param x Starting X position of vehicle
     * @param y Starting Y position of vehicle.
     * @param myDirection Starting Direction of vehicle.
     */
    protected AbstractVehicle(int x, int y, Direction myDirection) {
        initialX = x;
        initialY = y;

        currentX = x;
        currentY = y;
        currentDirection = myDirection;
    }


    /**
     * To decide which vehicle dies when two vehicles collide if any
     * @param theOther The other vehicle.
     */
    @Override
    public void collide(Vehicle theOther) {
        if (getDeathTime() > theOther.getDeathTime()) {
            deadOrAlive = false;
            getImageFileName();
        }
    }

    /**
     * Retrieves the image of the specific vehicle, also will check if
     * dead or alive and grab appropriate image.
     * @return The dead/alive image of vehicle.
     */
    @Override
    public String getImageFileName() {
        return getClass().getSimpleName().toLowerCase() + (deadOrAlive ? ".gif" : "_dead.gif");

    }

    /**
     * Returns the alive state of the vehicle.
     * @return whether the vehicle is alive.
     */
    @Override
    public boolean isAlive() {
        return deadOrAlive;
    }

    /**
     * Pokes the vehicle while dead to check if it is able to revive.
     * If not adds one to the poke counter.
     */
    @Override
    public void poke() {
        poked++;
        if (poked >= getDeathTime()) {
            setDirection(Direction.random());
            deadOrAlive = true;
            poked = 0;
        }
    }

    /**
     * Will return the vehicle to its starting position.
     */
    @Override
    public void reset() {
        setX(initialX);
        setY(initialY);
    }

    /**
     * Will set the vehicles X position to the new passed X-coordinate.
     * @param theX The new x-coordinate.
     */
    @Override
    public void setX(int theX) {
        currentX = theX;
    }

    /**
     * Will set the vehicles Y position to the new passed Y-coordinate.
     * @param theY The new y-coordinate.
     */
    @Override
    public void setY(int theY) {
        currentY = theY;
    }

    /**
     * Returns the current X position of the vehicle.
     * @return The current X-coordinate.
     */
    @Override
    public int getX() {
        return currentX;
    }

    /**
     * Returns the current Y position of the vehicle.
     * @return The current Y-coordinate.
     */
    @Override
    public int getY() {
        return currentY;
    }

    /**
     * Will set the Direction of the vehicle to the new passed Direction.
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(Direction theDir) {
        currentDirection = theDir;
    }

    /**
     * Give the current Direction of the vehicle.
     * @return the current Direction of the vehicle.
     */
    @Override
    public Direction getDirection() {
        return currentDirection;
    }

    /**
     * Overrides the toString() method to return the name of the class.
     * @return The name of the Class.
     */
    @Override
    public String toString(){
        return getClass().getSimpleName();
    }
}
