package entity.animal;
import services.parameters.Direction;
import services.parameters.Location;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    public void move(Object animal, ArrayList<Object> objectArrayList, int x, int y, int locationSize, int moveSpeed) {
        int move = moveSpeed;
        Direction direction;
        while (move != 0) {
            direction = Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
            if (direction.name().equals("UP")) {
                if (x - 1 >= 0) {
                    x -= 1;
                    move--;
                }
            }
            if (direction.name().equals("DOWN")) {
                if (x + 1 < Location.LOCATION_ISLAND.length) {
                    x += 1;
                    move--;
                }
            }
            if (direction.name().equals("RIGHT")) {
                if (y + 1 < locationSize) {
                    y += 1;
                    move--;
                }
            }
            if (direction.name().equals("LEFT")) {
                if (y - 1 >= 0 && y - 1 <= locationSize) {
                    y -= 1;
                    move--;
                }
            }
            if (move == 0) {
                ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[x][y];
                if (arrayList == objectArrayList) {
                    move = moveSpeed;
                }
            }
        }
        ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[x][y];
        if (checkTypeAnimalOnLocation(arrayList)) {
            arrayList.add(animal);
            objectArrayList.remove(animal);
        }
    }


    public void eat(ArrayList<Object> objectArrayList) {

    }

    public void multiply(ArrayList<Object> objectArrayList, int countListSize) {
    }

    public boolean checkTypeAnimal(Object o1) {
        return false;
    }

    public boolean checkAbleToEat(int chance) {
        return false;
    }

    public void checkPlantOnLocation() {
    }

    public boolean checkTypeAnimalOnLocation(ArrayList<Object> objectArrayList) {
        return false;
    }
}
