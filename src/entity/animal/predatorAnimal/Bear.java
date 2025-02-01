package entity.animal.predatorAnimal;

import entity.animal.Animal;
import entity.animal.herbivoreAnimal.*;
import services.parameters.Statistics;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator {
    public double weightAnimal = 500;

    public final int MAX_COUNT_ON_FIELD = 5;
    public final int MOVE_SPEED = 2;
    public final double KILOGRAMS_OF_FOOD = 80;
    public final int WEIGHT_ANIMAL = 500;

    public final int CHANCE_EAT_RABBIT = 80;
    public final int CHANCE_EAT_HORSE = 40;
    public final int CHANCE_EAT_DEER = 80;
    public final int CHANCE_EAT_MOUSE = 90;
    public final int CHANCE_EAT_GOAT = 70;
    public final int CHANCE_EAT_SHEEP = 70;
    public final int CHANCE_EAT_HOG = 50;
    public final int CHANCE_EAT_BUFFALO = 20;
    public final int CHANCE_EAT_DUCK = 10;
    public final int CHANCE_EAT_SNAKE = 80;


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
    public void eat(ArrayList<Animal> animalArrayList, Animal animal) {//snake
        double weightAnimal = animal.getWeightAnimal();
        double weight = (animal.getWeightAnimal() / animal.getWeight_animalFinal()) * 100;
        if (weight <= 40) {
            animalArrayList.remove(animal);
            Statistics.the_number_animals_killed_starvation++;
            return;
        }
        if (animal.getWeightAnimal() == animal.getWeight_animalFinal()) {
            weightAnimal -= animal.getKilogramsOfFood();
            animal.setWeightAnimal(weightAnimal);
            return;
        }
        if (animal.getWeightAnimal() <= 0) {
            animalArrayList.remove(animal);
            Statistics.the_number_animals_killed_starvation++;
            return;
        }
        for (int k = 0; k < animalArrayList.size(); k++) {
            Animal animal1 = animalArrayList.get(k);
            if (animal1 instanceof Herbivore || animal1 instanceof Snake) {
                double weightHerbivoreAnimal = animal1.getWeightAnimal();
                if (checkTypeAnimal(animal1)) {
                    animalArrayList.remove(k);
                    Statistics.number_animals_eaten++;
                    if (weightAnimal + weightHerbivoreAnimal > animal.getWeight_animalFinal()) {
                        animal.setWeightAnimal(animal.getWeight_animalFinal());
                        return;
                    }
                    animal.setWeightAnimal(weightAnimal += weightHerbivoreAnimal);
                    if (weightHerbivoreAnimal < animal.getKilogramsOfFood()) {
                        weightAnimal -= animal.getKilogramsOfFood() - weightHerbivoreAnimal;
                        animal.setWeightAnimal(weightAnimal);
                        return;
                    }
                    return;
                }
            }
        }
        weightAnimal -= animal.getKilogramsOfFood();
        animal.setWeightAnimal(weightAnimal);
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
        } else if (o instanceof Snake) {
            return checkAbleToEat(CHANCE_EAT_SNAKE);
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

