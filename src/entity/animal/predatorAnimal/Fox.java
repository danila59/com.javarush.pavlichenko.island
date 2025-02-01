package entity.animal.predatorAnimal;

import entity.animal.herbivoreAnimal.*;

import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator {

    public double weightAnimal = 8;

    public final int MAX_COUNT_ON_FIELD = 30;
    public final int MOVE_SPEED = 2;
    public final double KILOGRAMS_OF_FOOD = 2;
    public final int WEIGHT_ANIMAL = 8;

    public final int CHANCE_EAT_RABBIT = 70;
    public final int CHANCE_EAT_MOUSE = 90;
    public final int CHANCE_EAT_DUCK = 60;
    public final int CHANCE_EAT_CATERPILLAR = 40;


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
        } else if (o instanceof Mouse) {
            return checkAbleToEat(CHANCE_EAT_MOUSE);
        } else if (o instanceof Duck) {
            return checkAbleToEat(CHANCE_EAT_DUCK);
        } else if (o instanceof Caterpillar) {
            return checkAbleToEat(CHANCE_EAT_CATERPILLAR);
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
