package animal.herbivoreAnimal;

import entity.init.ListTypeAnimals;
import entity.parameters.Direction;
import entity.parameters.Location;
import entity.parameters.Statistics;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore {
    public double weightAnimal = 1;
    public static ArrayList<Object> objects = new ArrayList<>();

    public final int MAX_COUNT_ON_FIELD = 200;
    public final int MOVE_SPEED = 4;
    public final double KILOGRAMS_OF_FOOD = 0.15;
    public final int WEIGHT_ANIMAL = 1;
    public final int CHANCE_EAT_CATERPILLAR = 90;

    public double getWeightAnimal() {
        return weightAnimal;
    }


    @Override
    public void move(Object o, ArrayList<Object> objectArrayList, int x, int y, int locationSize) {
        int moveSpeed = MOVE_SPEED;
        Direction direction;
        while (moveSpeed != 0) {

            direction = Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
            if (direction.name().equals("UP")) {
                if (x - 1 >= 0) {
                    x -= 1;
                    moveSpeed--;
                }
            }
            if (direction.name().equals("DOWN")) {
                if (x + 1 < Location.LOCATION_ISLAND.length) {
                    x += 1;
                    moveSpeed--;
                }
            }
            if (direction.name().equals("RIGHT")) {
                if (y + 1 < locationSize) {
                    y += 1;
                    moveSpeed--;
                }
            }
            if (direction.name().equals("LEFT")) {
                if (y - 1 >= 0 && y - 1 <= locationSize) {
                    y -= 1;
                    moveSpeed--;
                }
            }
            if (moveSpeed == 0) {
                ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[x][y];
                if (arrayList == objectArrayList) {
                    moveSpeed = MOVE_SPEED;
                }
            }
        }

        ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[x][y];
        if (checkTypeAnimalOnLocation(arrayList)) {
            arrayList.add(o);
            objectArrayList.remove(o);
        }
    }


    @Override
    public boolean checkTypeAnimalOnLocation(ArrayList<Object> objectArrayList) {
        int count = 0;
        for (int i = 0; i < objectArrayList.size(); i++) {
            Object object = objectArrayList.get(i);
            if (object instanceof Duck) {
                count++;
            }
        }
        if (count >= MAX_COUNT_ON_FIELD) {
            return false;
        }
        return true;
    }

    @Override
    public void eat(ArrayList<Object> objectArrayList) {
        double field_value = 0;
        Field field;
        for (int k = 0; k < objectArrayList.size(); k++) {
            String simpleNameClass = objectArrayList.get(k).getClass().getSimpleName();
            Object object1 = objectArrayList.get(k);

            double weight = (getWeightAnimal() /WEIGHT_ANIMAL ) * 100;
            if(weight <= 40){
                objectArrayList.remove(k);
                Statistics.the_number_animals_killed_starvation++;
                return;
            }

            if (getWeightAnimal() == WEIGHT_ANIMAL) {
                weightAnimal -= KILOGRAMS_OF_FOOD;
                return;
            }
            if (getWeightAnimal() <= 0) {
                objectArrayList.remove(k);
                Statistics.the_number_animals_killed_starvation++;
                return;
            }
            for (int i = 0; i < ListTypeAnimals.PLANT_ARRAY_LIST.size(); i++) {
                String simpleNameClasHerbivore = ListTypeAnimals.PLANT_ARRAY_LIST.get(i).getClass().getSimpleName();
                Object object = ListTypeAnimals.PLANT_ARRAY_LIST.get(i);
                if (simpleNameClass.equals("Caterpillar")) {
                    try {
                        field = object1.getClass().getField("weightAnimal");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        field_value = (double) field.get(object1);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    if (checkTypeAnimal(object1)) {
                        objectArrayList.remove(k);
                        Statistics.number_animals_eaten++;
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
                    } else {
                        weightAnimal -= KILOGRAMS_OF_FOOD;
                        return;
                    }
                }
                if (simpleNameClass.equals(simpleNameClasHerbivore)) {
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

    @Override
    public void multiply(ArrayList<Object> objectArrayList, int countListSize) {
        int weight = WEIGHT_ANIMAL / 2 ;
        if(getWeightAnimal() <= weight)return;
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

        for (int i = 0; i < countListSize; i++) {
            Object o = objectArrayList.get(i);
            if (o instanceof Duck) {
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
                            if (o instanceof Duck) {
                                while (true) {
                                    if (j + 1 >= objectArrayList.size()) {
                                        return;
                                    }
                                    Object o2 = objectArrayList.get(j + 1);
                                    j++;
                                    if (o2 instanceof Duck) {
                                        if (!checkTypeAnimalOnLocation(objectArrayList)) return;
                                        objectArrayList.add(new Duck());
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
                if (o instanceof Duck) {
                    while (true) {
                        if (j + 1 >= objectArrayList.size()) {
                            return;
                        }
                        Object o2 = objectArrayList.get(j + 1);
                        if (o2 instanceof Duck) {
                            if (!checkTypeAnimalOnLocation(objectArrayList)) return;
                            objectArrayList.add(new Duck());
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
}

