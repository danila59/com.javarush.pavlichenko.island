package entity.animal.predatorAnimal;

import entity.animal.herbivoreAnimal.*;
import java.util.concurrent.ThreadLocalRandom;


public class Wolf extends Predator {
    public double weightAnimal = 50;

    public final int MAX_COUNT_ON_FIELD = 30;
    public final int MOVE_SPEED = 3;
    public final double KILOGRAMS_OF_FOOD = 8;
    public final int WEIGHT_ANIMAL = 50;

    public final int CHANCE_EAT_RABBIT = 60;
    public final int CHANCE_EAT_HORSE = 10;
    public final int CHANCE_EAT_DEER = 15;
    public final int CHANCE_EAT_MOUSE = 80;
    public final int CHANCE_EAT_GOAT = 60;
    public final int CHANCE_EAT_SHEEP = 70;
    public final int CHANCE_EAT_HOG = 15;
    public final int CHANCE_EAT_BUFFALO = 10;
    public final int CHANCE_EAT_DUCK = 40;



    @Override
    public double getKilogramsOfFood() {
        return KILOGRAMS_OF_FOOD;
    }

    @Override
    public int getMaxCountOnField() {
        return MAX_COUNT_ON_FIELD;
    }

    @Override
    public int getMoveSpeed() {
        return MOVE_SPEED;
    }

    @Override
    public double getWeight_animalFinal() {
        return WEIGHT_ANIMAL;
    }

    @Override
    public double getWeightAnimal() {
        return weightAnimal;
    }

    @Override
    public void setWeightAnimal(double weightAnimal) {
       this.weightAnimal = weightAnimal;
    }


    @Override
    public boolean checkTypeAnimal(Object o) {
        if (o instanceof Rabbit) {
            return checkAbleToEat(CHANCE_EAT_RABBIT);
        } else if (o instanceof Horse) {
            return checkAbleToEat(CHANCE_EAT_HORSE);
        } else if (o instanceof Deer) {
            return checkAbleToEat(CHANCE_EAT_DEER);
        } else if (o instanceof Mouse) {
            return checkAbleToEat(CHANCE_EAT_MOUSE);
        } else if (o instanceof Goat) {
            return checkAbleToEat(CHANCE_EAT_GOAT);
        } else if (o instanceof Sheep) {
            return checkAbleToEat(CHANCE_EAT_SHEEP);
        } else if (o instanceof Hog) {
            return checkAbleToEat(CHANCE_EAT_HOG);
        } else if (o instanceof Buffalo) {
            return checkAbleToEat(CHANCE_EAT_BUFFALO);
        } else if (o instanceof Duck) {
            return checkAbleToEat(CHANCE_EAT_DUCK);
        }
        return false;
    }

    @Override
    public boolean checkAbleToEat(int chance) {
        int result = ThreadLocalRandom.current().nextInt(1, 100);
        if (result >= chance) {
            return true;
        }
        return false;
    }
}
