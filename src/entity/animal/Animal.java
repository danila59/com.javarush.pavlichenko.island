package entity.animal;


import services.parameters.Direction;
import services.parameters.FabricAnimals;
import services.parameters.Location;
import services.parameters.Statistics;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    protected double weightAnimal;
    protected int maxCountOnField;
    protected int moveSpeed;
    protected double kilogramsOfFood;
    protected int weight_animalFinal;

    public double getWeightAnimal() {
        return weightAnimal;
    }

    public double getKilogramsOfFood() {
        return kilogramsOfFood;
    }

    public int getMaxCountOnField() {
        return maxCountOnField;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public double getWeight_animalFinal() {
        return weight_animalFinal;
    }

    public void setWeightAnimal(double weightAnimal) {
        this.weightAnimal = weightAnimal;
    }

    public void move(Animal animal, ArrayList<Animal> objectArrayList, int x, int y, int locationSize) {
        int move = animal.getMoveSpeed();
        Direction direction;
        while (move != 0) {
            direction = Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
            if (direction.name().equals(Direction.UP.name())) {
                if (x - 1 >= 0) {
                    x -= 1;
                    move--;
                }
            }
            if (direction.name().equals(Direction.DOWN.name())) {
                if (x + 1 < Location.LOCATION_ISLAND.length) {
                    x += 1;
                    move--;
                }
            }
            if (direction.name().equals(Direction.RIGHT.name())) {
                if (y + 1 < locationSize) {
                    y += 1;
                    move--;
                }
            }
            if (direction.name().equals(Direction.LEFT.name())) {
                if (y - 1 >= 0 && y - 1 <= locationSize) {
                    y -= 1;
                    move--;
                }
            }
            if (move == 0) {
                ArrayList<Animal> arrayList = Location.LOCATION_ISLAND[x][y];
                if (arrayList == objectArrayList) {
                    move = animal.getMoveSpeed();
                }
            }
        }
        ArrayList<Animal> arrayList = Location.LOCATION_ISLAND[x][y];
        if (checkTypeAnimalOnLocation(arrayList, animal)) {
            arrayList.add(animal);
            objectArrayList.remove(animal);
        }
    }

    public void eat(ArrayList<Animal> objectArrayList, Animal animal) {}

    public void multiply(ArrayList<Animal> animalArrayList, int countAnimals, Animal animal) {
        int maxCountOnField = getMaxCountOnField();
        if (countAnimals == maxCountOnField) return;
        int result = countAnimals / 2;
        if (countAnimals + result > maxCountOnField) {
            int numbers = maxCountOnField - countAnimals;
            while (numbers != 0) {
                int res = ThreadLocalRandom.current().nextInt(1, 100);
                if (res <= 70) {
                    numbers--;
                }
                if (numbers == 0) break;
                animalArrayList.add(FabricAnimals.creatAnimals(animal.getClass().getSimpleName()));
                Statistics.number_of_animals_born++;
                numbers--;
            }

        } else {
            while (result != 0) {
                int res = ThreadLocalRandom.current().nextInt(1, 100);
                if (res <= 70) {
                    result--;
                }
                if (result == 0) break;
                animalArrayList.add(FabricAnimals.creatAnimals(animal.getClass().getSimpleName()));
                Statistics.number_of_animals_born++;
                result--;
            }
        }
    }


    public boolean checkTypeAnimal(Object o1) {
        return false;
    }

    public boolean checkAbleToEat(int chance) {
        return false;
    }

    public void checkPlantOnLocation() {
    }

    public boolean checkTypeAnimalOnLocation(ArrayList<Animal> objectArrayList, Animal animal) {
        int count = 0;
        for (int i = 0; i < objectArrayList.size(); i++) {
            Animal animal1 = objectArrayList.get(i);
            if (animal1.getClass().getSimpleName().equals(animal.getClass().getSimpleName())) {
                count++;
            }
        }
        if (count >= animal.getMaxCountOnField()) {
            return false;
        }
        return true;
    }
}

