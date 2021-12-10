package tests;

import model.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void valueOf() {
    }

    @Test
    void letter() {
    }

    @Test
    void left() {
    }

    @Test
    void random() {
    }

    @Test
    void right() {
    }

    @Test
    void reverse() {
        Direction d = Direction.NORTH;
        assertEquals(Direction.SOUTH, d.reverse());

        d = Direction.WEST;
        assertEquals(Direction.EAST, d.reverse());
    }

    @Test
    void dx() {
    }

    @Test
    void dy() {
    }

    @Test
    void values() {
    }

    @Test
    void testValueOf() {
    }
}