package entity.animal.herbivoreAnimal;

import entity.animal.Animal;
import entity.animal.plant.Plant;
import services.parameters.Statistics;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivore {
    public double weightAnimal = 0.05;

    public final int MAX_COUNT_ON_FIELD = 500;
    public final int MOVE_SPEED = 1;
    public final double KILOGRAMS_OF_FOOD = 0.01;
    public final double WEIGHT_ANIMAL = 0.05;
    public final int CHANCE_EAT_CATERPILLAR = 90;

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
    public void eat(ArrayList<Animal> animalArrayList, Animal animal) {
        double weightAnimal = animal.getWeightAnimal();
        double weight = (animal.getWeightAnimal() / animal.getWeight_animalFinal()) * 100;
        if (weight <= 50) {
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
            if (animal1 instanceof Plant) {
                double weightPlant = ((Plant) animal1).getWeightPlant();
                animalArrayList.remove(k);
                Statistics.amount_of_grass_eaten++;
                if (weightAnimal + weightPlant > animal.getWeight_animalFinal()) {
                    animal.setWeightAnimal(animal.getWeight_animalFinal());
                    return;
                }
                animal.setWeightAnimal(weightAnimal += weightPlant);
                if (weightPlant < animal.getKilogramsOfFood()) {
                    weightAnimal -= animal.getKilogramsOfFood() - weightPlant;
                    animal.setWeightAnimal(weightAnimal);
                    return;
                }
                return;
            }
            if (animal1 instanceof Caterpillar){
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
        if (o instanceof Caterpillar) {
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

