package model;

public abstract class AbstractVehicle implements Vehicle {
    protected Direction rndDirection = Direction.random();

    private static int poked = 0;

    private static boolean deadOrAlive = true;

    private final int initialX;

    private final int initialY;

    protected static Direction currentDirection;

    protected static int currentX;

    protected static int currentY;


    protected AbstractVehicle(int x, int y, Direction myDirection) {
        this.initialX = x;
        this.initialY = y;

        currentX = x;
        currentY = y;
        currentDirection = myDirection;
    }


    @Override
    public void collide(Vehicle theOther) {
        if (this.getDeathTime() < theOther.getDeathTime()) {
            deadOrAlive = false;
            //change image
            //to get dead image call getClass().getSimpleName().toLowerCase() + "_dead.gif"
        }
    }

    @Override
    public String getImageFileName() {
        return getClass().getSimpleName().toLowerCase() + ".gif";
    }

    @Override
    public boolean isAlive() {
        return deadOrAlive;
    }

    @Override
    public void poke() {
        if (this.isAlive()) {
            setDirection(rndDirection);
            poked = 0;
        } else {
            poked++;
        }
    }

    @Override
    public void reset() {
        setX(initialX);
        setY(initialY);
    }

    @Override
    public void setDirection(Direction theDir) {

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
    public Direction getDirection() {
        return null;
    }
}
