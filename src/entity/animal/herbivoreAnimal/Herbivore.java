package entity.animal.herbivoreAnimal;

import entity.animal.Animal;

import java.util.ArrayList;

public abstract class Herbivore extends Animal {
    @Override
    public void move(Object o, ArrayList<Object> objectArrayList, int x, int y,  int locationSize ) {
    }

    @Override
    public void eat(ArrayList<Object> objectArrayList) {}

    @Override
    public void multiply(ArrayList<Object> objectArrayList,int countListSize) {}

    @Override
    public void checkPlantOnLocation() {
        super.checkPlantOnLocation();
    }
}
