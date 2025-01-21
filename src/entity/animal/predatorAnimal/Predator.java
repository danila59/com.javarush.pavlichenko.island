package entity.animal.predatorAnimal;

import entity.animal.Animal;

import java.util.ArrayList;

public abstract class Predator extends Animal {
    @Override
    public void move(Object o, ArrayList<Object> objectArrayList, int x, int y,  int locationSize ) {}
    @Override
    public void eat(ArrayList<Object> objectArrayList) {}
    @Override
    public void multiply(ArrayList<Object> objectArrayList,int countListSize) {}
}
