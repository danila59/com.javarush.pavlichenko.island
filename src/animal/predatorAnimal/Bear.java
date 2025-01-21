package animal.predatorAnimal;

import animal.herbivoreAnimal.*;
import entity.init.ListTypeAnimals;
import entity.parameters.Direction;
import entity.parameters.Location;
import entity.parameters.Statistics;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator {
    public double weightBear = 500;
    public static ArrayList<Object> objects = new ArrayList<>();

    public final int MAX_COUNT_ON_FIELD = 5;
    public final int MOVE_SPEED = 2;
    public final double KILOGRAMS_OF_FOOD = 80;
    public final int WEIGHT_BEAR_FINAL = 500;

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


    public double getWeightBear() {
        return weightBear;
    }

    @Override
    public void move(Object o, ArrayList<Object> objectArrayList, int x, int y,  int locationSize) {
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
            if (object instanceof Bear) {
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

            double weight = (getWeightBear() /WEIGHT_BEAR_FINAL ) * 100;
            if(weight <= 40){
                objectArrayList.remove(k);
                Statistics.the_number_animals_killed_starvation++;
                return;
            }
            if (getWeightBear() == WEIGHT_BEAR_FINAL) {
                weightBear -= KILOGRAMS_OF_FOOD;
                return;
            }
            if (getWeightBear() <= 0) {
                objectArrayList.remove(k);
                Statistics.the_number_animals_killed_starvation++;
                return;
            }
            for (int i = 0; i < ListTypeAnimals.HERBIVORE_ARRAY_LIST.size(); i++) {
                String simpleNameClasHerbivore = ListTypeAnimals.HERBIVORE_ARRAY_LIST.get(i).getClass().getSimpleName();
                Object object = ListTypeAnimals.HERBIVORE_ARRAY_LIST.get(i);
                if (simpleNameClass.equals("Snake")){
                    try {
                        field = object1.getClass().getField("weightSnake");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        field_value = (double) field.get(object1);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    if (checkTypeAnimal(object)) {
                        objectArrayList.remove(k);
                        Statistics.number_animals_eaten++;
                        if (weightBear + field_value > WEIGHT_BEAR_FINAL) {
                            weightBear = WEIGHT_BEAR_FINAL;
                            return;
                        }
                        weightBear += field_value;
                        if (field_value < KILOGRAMS_OF_FOOD) {
                            weightBear -= KILOGRAMS_OF_FOOD - field_value;
                            return;
                        }
                        return;
                    } else {
                        weightBear -= KILOGRAMS_OF_FOOD;
                        return;
                    }
                }
                if (simpleNameClass.equals(simpleNameClasHerbivore)) {
                    try {
                        field = object.getClass().getField("weightAnimal");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        field_value = (double) field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    if (checkTypeAnimal(object)) {
                        objectArrayList.remove(k);
                        Statistics.number_animals_eaten++;
                        if (weightBear + field_value > WEIGHT_BEAR_FINAL) {
                            weightBear = WEIGHT_BEAR_FINAL;
                            return;
                        }
                        weightBear += field_value;
                        if (field_value < KILOGRAMS_OF_FOOD) {
                            weightBear -= KILOGRAMS_OF_FOOD - field_value;
                            return;
                        }
                        return;
                    } else {
                        weightBear -= KILOGRAMS_OF_FOOD;
                        return;
                    }
                }
            }
        }
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
        }else if (o instanceof Snake) {
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

    @Override
    public void multiply(ArrayList<Object> objectArrayList, int countListSize) {
        int count = 0;
        int check = 0;
        int weight = WEIGHT_BEAR_FINAL / 2 ;
        if(getWeightBear() <= weight)return;
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
            if (o instanceof Bear) {
                count++;
            }
        }
        int result = count / 2;

        if (check == objects.size() && !objects.isEmpty() && result % 2 != 0) {
            return;
        }
        if (check == count) return;

        if (!objects.isEmpty()) {
            Object o = null;
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
                            if (o instanceof Bear) {
                                while (true) {
                                    if (j + 1 >= objectArrayList.size()) {
                                        return;
                                    }
                                    Object o2 = objectArrayList.get(j + 1);
                                    j++;
                                    if (o2 instanceof Bear) {
                                        if (!checkTypeAnimalOnLocation(objectArrayList)) return;
                                        objectArrayList.add(new Bear());
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
                if (o instanceof Bear) {
                    while (true) {
                        if (j + 1 >= objectArrayList.size()) {
                            return;
                        }
                        Object o2 = objectArrayList.get(j + 1);
                        if (o2 instanceof Bear) {
                            if (!checkTypeAnimalOnLocation(objectArrayList)) return;
                            objectArrayList.add(new Bear());
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

