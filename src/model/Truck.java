package model;

import java.util.Map;

public class Truck extends AbstractVehicle {

    public Truck(int x, int y, Direction myDirection) {
        super(x,y,myDirection);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return false;
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return null;
    }


    @Override
    public int getDeathTime() {
        //truck can't die
        return 0;
    }

    @Override
    public String toString(){
        return null;
    }
}
