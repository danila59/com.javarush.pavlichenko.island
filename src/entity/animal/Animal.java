package entity.animal;

import java.util.ArrayList;

public abstract class Animal {

    public void move(Object o, ArrayList<Object> objectArrayList, int x, int y ,  int locationSize) {}

    public void eat(ArrayList<Object> objectArrayList){}

    public void multiply(ArrayList<Object> objectArrayList,int countListSize){}

    public  boolean checkTypeAnimal(Object o1){
        return false;
    }

    public boolean checkAbleToEat(int chance){
        return false;
    }

    public void checkPlantOnLocation(){}

    public boolean checkTypeAnimalOnLocation(ArrayList<Object>objectArrayList ){
        return false;
    }
}
