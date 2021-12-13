package tests;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Class ATV
 *
 * @author Xavier Hines
 * @version 1.0
 */
class AtvTest {

    /**
     * A large number of attempts for methods that need to return a random value.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /**
     * Test method for ATV constructor.
     */
    @Test
    public void testAtvConstructor() {

        final Atv atv = new Atv(10, 11, Direction.NORTH);

        assertEquals(10, atv.getX(),"ATV x coordinate not initialized correctly!");
        assertEquals( 11, atv.getY(), "ATV y coordinate not initialized correctly!");
        assertEquals(Direction.NORTH, atv.getDirection(),"ATV direction not initialized correctly!" );
        assertEquals( 25, atv.getDeathTime(), "ATV death time not initialized correctly!");
        Assertions.assertTrue(atv.isAlive(), "ATV isAlive() fails initially!");

    }

    /**
     * Test method for Atv collide.
     */
    @Test
    void collide() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        final Human human = new Human(0, 0, Direction.NORTH);
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        final Car car = new Car(0, 0, Direction.NORTH);
        final Bicycle bicycle = new Bicycle(0, 0, Direction.NORTH);
        final Taxi taxi = new Taxi(0, 0, Direction.NORTH);

        atv.collide(human);
        assertTrue(atv.isAlive(), "ATV shouldn't die to colliding with Human");

        atv.collide(bicycle);
        assertTrue(atv.isAlive(), "ATV shouldn't die to colliding with Bicycle");

        atv.collide(atv);
        assertTrue(atv.isAlive(), "ATV shouldn't die to colliding with ATV");

        atv.collide(truck);
        assertFalse(atv.isAlive(), "ATV should die to colliding with Truck");

        atv.collide(taxi);
        assertFalse(atv.isAlive(), "ATV should die to colliding with Taxi");

        atv.collide(car);
        assertFalse(atv.isAlive(), "ATV should die to colliding with car");
    }

    /**
     * Test method for Atv getImageFilename
     */
    @Test
    void getImageFileName() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        final Truck truck = new Truck(0, 0, Direction.NORTH);

        assertEquals("atv.gif", atv.getImageFileName(), "Isn't returning the correct image file name");

        atv.collide(truck);
        assertEquals("atv_dead.gif", atv.getImageFileName(), "Isn't returning _dead.gif image file");
    }

    /**
     * Test method for Atv isAlive()
     */
    @Test
    void isAlive() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        final Truck truck = new Truck(0, 0, Direction.NORTH);

        assertTrue(atv.isAlive(), "ATV should be alive on initialization");

        atv.collide(truck);
        assertFalse(atv.isAlive(),"ATV should be dead after hitting truck");
    }

    /**
     * Test method for Atv poke();
     */
    @Test
    void poke() {

    }

    /**
     * Test method for Atv reset();
     */
    @Test
    void reset() {
        final Atv atv = new Atv(10, 15, Direction.NORTH);

        atv.setX(5);
        atv.setY(17);

        atv.reset();
        assertEquals(10, atv.getX(), "Doesn't return initial X position");
        assertEquals(15, atv.getY(), "Doesn't return initial Y position");
    }

    /**
     * Test method for Atv setX
     */
    @Test
    void setX() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);

        atv.setX(15);
        assertEquals(15, atv.getX(), "Doesn't return the value X was set to");
    }

    /**
     * Test method for Atv setY
     */
    @Test
    void setY() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);

        atv.setY(20);
        assertEquals(20, atv.getY(), "Doesn't return the value Y was set to");
    }

    /**
     * Test method for Atv getX
     */
    @Test
    void getX() {
        final Atv atv = new Atv(23, 0, Direction.NORTH);

        assertEquals(23, atv.getX(), "Doesn't return the current value of X");
    }

    /**
     * Test method for Atv getY
     */
    @Test
    void getY() {
        final Atv atv = new Atv(0, 14, Direction.NORTH);

        assertEquals(14, atv.getY(), "Doesn't return the current value of Y");
    }

    /**
     * Test method for Atv setDirection
     */
    @Test
    void setDirection() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);

        atv.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, atv.getDirection(), "The direction is not correctly assigned by setDirection");
    }

    /**
     * Test method for Atv getDirection
     */
    @Test
    void getDirection() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);

        assertEquals(Direction.NORTH, atv.getDirection(), "The direction isn't reported correctly by getDirection.");
    }

    /**
     * Test method for Atv toString
     */
    @Test
    void testToString() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);

        assertEquals("Atv", atv.toString(), "Incorrectly retrieves name of object");
    }

    /**
     * Test method for {@link Atv#canPass(Terrain, Light)}
     */
    @Test
    void canPass() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);

        assertFalse(atv.canPass(Terrain.WALL, Light.GREEN));
    }

    /**
     * Test method for {@link Atv#chooseDirection(Map)}
     */
    @Test
    void chooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();

        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        final Atv atv = new Atv(0, 0, Direction.NORTH);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = atv.chooseDirection(neighbors);

            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }



        Assertions.assertTrue(seenWest && seenNorth && seenEast && seenSouth, "ATV chooseDirection() fails to randomly select" +
                "a direction");

    }

    /**
     * Test method for {@link Atv#getDeathTime()}
     */
    @Test
    void getDeathTime() {
        final Atv atv = new Atv(0, 0, Direction.NORTH);
        assertEquals(25, atv.getDeathTime(), "Doesn't retrieve correct death time.");

    }
}