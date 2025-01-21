package animal.herbivoreAnimal;

import java.util.ArrayList;

public class Caterpillar extends Herbivore {

    public double weightAnimal = 0.01;
    public static ArrayList<Object> objects = new ArrayList<>();

    public final int MAX_COUNT_ON_FIELD = 1000;
    public final double WEIGHT_ANIMAL = 0.01;


    public double getWeightAnimal() {
        return weightAnimal;
    }


    @Override
    public boolean checkTypeAnimalOnLocation(ArrayList<Object> objectArrayList) {
        int count = 0;
        for (int i = 0; i < objectArrayList.size(); i++) {
            Object object = objectArrayList.get(i);
            if (object instanceof Caterpillar) {
                count++;
            }
        }
        if (count >= MAX_COUNT_ON_FIELD) {
            return false;
        }
        return true;
    }

    public int checkTypeAnimal(ArrayList<Object> objectArrayList) {
        int count = 0;
        for (int i = 0; i < objectArrayList.size(); i++) {
            Object object = objectArrayList.get(i);
            if (object instanceof Caterpillar) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void eat(ArrayList<Object> objectArrayList) {

    }


    @Override
    public void multiply(ArrayList<Object> objectArrayList, int countListSize) {
        int count = 0;
        if (!checkTypeAnimalOnLocation(objectArrayList))return;
        for (int i = 0; i < countListSize; i++) {
            Object o = objectArrayList.get(i);
            if (o instanceof Caterpillar) {
                count++;
            }
        }
        int result = count / 2;

        if (count + result > MAX_COUNT_ON_FIELD) {
            int rr = MAX_COUNT_ON_FIELD - count;
            while (rr != 0) {
                objectArrayList.add(new Caterpillar());
                rr--;
            }
        }
    }
}



