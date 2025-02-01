package entity.animal.herbivoreAnimal;

import entity.animal.Animal;
import entity.animal.plant.Plant;
import services.parameters.Statistics;
import java.util.ArrayList;

public abstract class Herbivore extends Animal {
    protected double weightAnimal;
    protected int maxCountOnField;
    protected int moveSpeed;
    protected double kilogramsOfFood;
    protected double weight_animalFinal;

    @Override
    public double getKilogramsOfFood() {
        return kilogramsOfFood;
    }

    @Override
    public int getMaxCountOnField() {
        return maxCountOnField;
    }

    @Override
    public int getMoveSpeed() {
        return moveSpeed;
    }

    @Override
    public double getWeight_animalFinal() {
        return weight_animalFinal;
    }

    @Override
    public double getWeightAnimal() {
        return weightAnimal;
    }

    @Override
    public void setWeightAnimal(double weightAnimal) {
        super.setWeightAnimal(weightAnimal);
    }

    @Override
    public void eat(ArrayList<Animal> animalArrayList, Animal animal) {
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
            if(animal1 instanceof Plant) {
                    double weightPlant =((Plant) animal1).getWeightPlant();
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
            }
        weightAnimal -= animal.getKilogramsOfFood();
        animal.setWeightAnimal(weightAnimal);
    }


    @Override
    public void checkPlantOnLocation() {
        super.checkPlantOnLocation();
    }
}
