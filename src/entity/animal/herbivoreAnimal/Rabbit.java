package entity.animal.herbivoreAnimal;

public class Rabbit extends Herbivore {
    public double weightAnimal = 2;

    public final int MAX_COUNT_ON_FIELD = 150;
    public final int MOVE_SPEED = 2;
    public final double KILOGRAMS_OF_FOOD = 0.45d;
    public final int WEIGHT_ANIMAL = 2;

    @Override
    public double getWeightAnimal() {
        return weightAnimal;
    }

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
    public void setWeightAnimal(double weightAnimal) {
        this.weightAnimal = weightAnimal;
    }


}

