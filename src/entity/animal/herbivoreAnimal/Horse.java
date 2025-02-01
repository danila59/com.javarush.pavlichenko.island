package entity.animal.herbivoreAnimal;

public class Horse extends Herbivore {
    public double weightAnimal = 400;


    public final int MAX_COUNT_ON_FIELD = 20;
    public final int MOVE_SPEED = 4;
    public final double KILOGRAMS_OF_FOOD = 60;
    public final int WEIGHT_ANIMAL = 400;

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


