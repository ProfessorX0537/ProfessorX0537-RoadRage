package model;
/**
 *
 * @Author
 * @version
 */
public abstract class AbstractVehicle implements Vehicle {

    private  int poked = 0;

    private  boolean deadOrAlive = true;

    private final int initialX;

    private final int initialY;

    protected Direction currentDirection;

    private int currentX;

    private int currentY;


    protected AbstractVehicle(int x, int y, Direction myDirection) {
        initialX = x;
        initialY = y;

        currentX = x;
        currentY = y;
        currentDirection = myDirection;
    }


    @Override
    public void collide(Vehicle theOther) {
        if (getDeathTime() > theOther.getDeathTime()) {
            deadOrAlive = false;
            getImageFileName();
        }
    }

    @Override
    public String getImageFileName() {
        return getClass().getSimpleName().toLowerCase() + (deadOrAlive ? ".gif" : "_dead.gif");

    }

    @Override
    public boolean isAlive() {
        return deadOrAlive;
    }

    @Override
    public void poke() {
        poked++;
        if (poked >= getDeathTime()) {
            setDirection(Direction.random());
            deadOrAlive = true;
            poked = 0;
        }
    }

    @Override
    public void reset() {
        setX(initialX);
        setY(initialY);
    }

    @Override
    public void setX(int theX) {
        currentX = theX;
    }

    @Override
    public void setY(int theY) {
        currentY = theY;
    }

    @Override
    public int getX() {
        return currentX;
    }

    @Override
    public int getY() {
        return currentY;
    }

    @Override
    public void setDirection(Direction theDir) {
        currentDirection = theDir;
    }

    @Override
    public Direction getDirection() {
        return currentDirection;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName().toLowerCase();
    }
}
