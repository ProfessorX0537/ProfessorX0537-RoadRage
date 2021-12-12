package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Human extends AbstractVehicle {
    public Human(int x, int y, Direction myDirection) {
        super(x, y, myDirection);

    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return true;

    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        Direction[] directions = {currentDirection, currentDirection.left(), currentDirection.right()};
        Collections.shuffle(Arrays.asList(directions));

        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.CROSSWALK)) return testDirection;

        }

        for (Direction testDirection : directions) {
            if (theNeighbors.get(testDirection).equals(Terrain.GRASS) ||
                    theNeighbors.get(testDirection).equals(Terrain.TRAIL)) return testDirection;
        }

        return currentDirection.reverse();
    }

    @Override
    public int getDeathTime() {
        return 45;
    }
}
