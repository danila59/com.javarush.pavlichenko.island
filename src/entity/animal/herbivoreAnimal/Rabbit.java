package entity.animal.herbivoreAnimal;


import entity.animal.plant.Grass;
import services.init.ListTypeAnimals;
import services.parameters.Statistics;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;


public class Rabbit extends Herbivore {
    public double weightAnimal = 2;
    public static ArrayList<Object> objects = new ArrayList<>();

    public int MAX_COUNT_ON_FIELD = 150;
    public int MOVE_SPEED = 2;
    public double KILOGRAMS_OF_FOOD = 0.45d;
    public int WEIGHT_ANIMAL = 2;


    public double getWeightAnimal() {
        return weightAnimal;
    }


    @Override
    public boolean checkTypeAnimalOnLocation(ArrayList<Object> objectArrayList) {
        int count = 0;
        for (int i = 0; i < objectArrayList.size(); i++) {
            Object object = objectArrayList.get(i);
            if (object instanceof Rabbit) {
                count++;
            }
        }
        if (count >= MAX_COUNT_ON_FIELD) {
            return false;
        }
        return true;
    }

    @Override
    public void move(Object animal, ArrayList<Object> objectArrayList, int x, int y, int locationSize, int moveSpeed) {
        super.move(animal, objectArrayList, x, y, locationSize, MOVE_SPEED);
    }

    @Override
    public void eat(ArrayList<Object> objectArrayList) {
        double field_value;
        Field field;
        for (int k = 0; k < objectArrayList.size(); k++) {
            String simpleNameClass = objectArrayList.get(k).getClass().getSimpleName();

            double weight = (weightAnimal / WEIGHT_ANIMAL) * 100;
            if (weight <= 40) {
                objectArrayList.remove(k);
                Statistics.the_number_animals_killed_starvation++;
                return;
            }

            if (weightAnimal == WEIGHT_ANIMAL) {
                weightAnimal -= KILOGRAMS_OF_FOOD;
                return;
            }

            if (weightAnimal <= 0) {
                objectArrayList.remove(k);
                Statistics.the_number_animals_killed_starvation++;
                return;
            }

            for (int i = 0; i < ListTypeAnimals.PLANT_ARRAY_LIST.size(); i++) {
                String simpleNameClasPlant = ListTypeAnimals.PLANT_ARRAY_LIST.get(i).getClass().getSimpleName();
                Object object = ListTypeAnimals.PLANT_ARRAY_LIST.get(i);
                if (simpleNameClass.equals(simpleNameClasPlant)) {
                    try {
                        field = object.getClass().getField("weightPlant");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        field_value = (double) field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    objectArrayList.remove(k);
                    Statistics.amount_of_grass_eaten++;

                    if (weightAnimal + field_value > WEIGHT_ANIMAL) {
                        weightAnimal = WEIGHT_ANIMAL;
                        return;
                    }
                    weightAnimal += field_value;
                    if (field_value < KILOGRAMS_OF_FOOD) {
                        weightAnimal -= KILOGRAMS_OF_FOOD - field_value;
                        return;
                    }
                    return;
                }
            }
        }
        weightAnimal -= KILOGRAMS_OF_FOOD;
    }

    @Override
    public void multiply(ArrayList<Object> objectArrayList, int countListSize) {
        int res = ThreadLocalRandom.current().nextInt(1, 100);
        if (res <= 50) return;
        int weight = WEIGHT_ANIMAL / 2;
        if (getWeightAnimal() <= weight) return;
        int count = 0;
        int check = 0;
        if (!objects.isEmpty()) {
            for (int i = 0; i < objects.size(); i++) {
                for (int i1 = 0; i1 < objectArrayList.size(); i1++) {
                    Object o = objects.get(i);
                    Object o1 = objectArrayList.get(i1);
                    if (o == o1) {
                        check++;
                    }
                }
            }
            if (check == 0) {
                objects.removeAll(Collections.unmodifiableList(objects));
            }
        }

        for (int i = 0; i < countListSize; i++) {//подсчитывает пары
            Object o = objectArrayList.get(i);
            if (o instanceof Rabbit) {
                count++;
            }
        }
        int result = count / 2;

        if (check == objects.size() && !objects.isEmpty() && result % 2 != 0) {
            return;
        }
        if (check == count) return;

        if (!objects.isEmpty()) {
            Object o;
            int repeat = 0;
            while (true) {
                for (int j = 0; j < countListSize; j++) {
                    for (int i = 0; i < objects.size(); i++) {
                        o = objectArrayList.get(j);
                        Object o1 = objects.get(i);
                        if (o == o1) {
                            repeat++;
                            break;
                        }
                        if (repeat == objects.size()) {
                            if (o instanceof Rabbit) {
                                while (true) {
                                    if (j + 1 >= objectArrayList.size()) {
                                        return;
                                    }
                                    Object o2 = objectArrayList.get(j + 1);
                                    j++;
                                    if (o2 instanceof Rabbit) {
                                        if (!checkTypeAnimalOnLocation(objectArrayList)) return;
                                        objectArrayList.add(new Rabbit());
                                        Statistics.number_of_animals_born++;
                                        objects.add(o);
                                        objects.add(o2);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (int j = 0; j < countListSize; j++) {
                Object o = objectArrayList.get(j);
                if (o instanceof Rabbit) {
                    while (true) {
                        if (j + 1 >= objectArrayList.size()) {
                            return;
                        }
                        Object o2 = objectArrayList.get(j + 1);
                        if (o2 instanceof Rabbit) {
                            if (!checkTypeAnimalOnLocation(objectArrayList)) return;
                            objectArrayList.add(new Rabbit());
                            Statistics.number_of_animals_born++;
                            objects.add(o);
                            objects.add(o2);
                            return;
                        }
                        j++;
                    }
                }
            }
        }
    }


    @Override
    public void checkPlantOnLocation() {
        super.checkPlantOnLocation();
    }
}
