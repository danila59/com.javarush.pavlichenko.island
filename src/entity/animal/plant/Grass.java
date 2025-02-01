package entity.animal.plant;

public class Grass extends Plant{

    public double weightPlant = 1;

    public final int  MAX_COUNT_ON_FIELD = 200;
    public final int  WEIGHT_PLANT = 1;

    @Override
    public int getMaxCountOnField() {
        return MAX_COUNT_ON_FIELD;
    }

    @Override
    public double getWeightPlant() {
        return weightPlant;
    }
}
