package entity.animal.herbivoreAnimal;

public class Sheep extends Herbivore {
    public double weightAnimal = 70;

    public final int MAX_COUNT_ON_FIELD = 140;
    public final int MOVE_SPEED = 3;
    public final double KILOGRAMS_OF_FOOD = 15;
    public final int WEIGHT_ANIMAL = 70;

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

}

