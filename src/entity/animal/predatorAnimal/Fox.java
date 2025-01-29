package entity.animal.predatorAnimal;

import entity.animal.herbivoreAnimal.Caterpillar;
import entity.animal.herbivoreAnimal.Duck;
import entity.animal.herbivoreAnimal.Mouse;
import entity.animal.herbivoreAnimal.Rabbit;
import services.init.ListTypeAnimals;
import services.parameters.Direction;
import services.parameters.Location;
import services.parameters.Statistics;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator {

    public double weightFox = 8;
    public static ArrayList<Object> objects = new ArrayList<>();

    public final int MAX_COUNT_ON_FIELD = 30;
    public final int MOVE_SPEED = 2;
    public final double KILOGRAMS_OF_FOOD = 2;
    public final int WEIGHT_FOX_FINAL = 8;

    public final int CHANCE_EAT_RABBIT = 70;
    public final int CHANCE_EAT_MOUSE = 90;
    public final int CHANCE_EAT_DUCK = 60;
    public final int CHANCE_EAT_CATERPILLAR = 40;

    public double getWeightFox() {
        return weightFox;
    }


    @Override
    public boolean checkTypeAnimalOnLocation(ArrayList<Object> objectArrayList) {
        int count = 0;
        for (int i = 0; i < objectArrayList.size(); i++) {
            Object object = objectArrayList.get(i);
            if (object instanceof Fox) {
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
        double field_value = 0;
        Field field;
        for (int k = 0; k < objectArrayList.size(); k++) {
            String simpleNameClass = objectArrayList.get(k).getClass().getSimpleName();

            double weight = (getWeightFox() / WEIGHT_FOX_FINAL) * 100;
            if (weight <= 40) {
                objectArrayList.remove(k);
                Statistics.the_number_animals_killed_starvation++;
                return;
            }
            if (getWeightFox() == WEIGHT_FOX_FINAL) {
                weightFox -= KILOGRAMS_OF_FOOD;
                return;
            }
            if (getWeightFox() <= 0) {
                objectArrayList.remove(k);
                Statistics.the_number_animals_killed_starvation++;
                return;
            }
            for (int i = 0; i < ListTypeAnimals.HERBIVORE_ARRAY_LIST.size(); i++) {
                String simpleNameClasHerbivore = ListTypeAnimals.HERBIVORE_ARRAY_LIST.get(i).getClass().getSimpleName();
                Object object = ListTypeAnimals.HERBIVORE_ARRAY_LIST.get(i);
                if (simpleNameClass.equals(simpleNameClasHerbivore) || simpleNameClass.equals("Caterpillar")) {
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
                        if (weightFox + field_value > WEIGHT_FOX_FINAL) {
                            weightFox = WEIGHT_FOX_FINAL;
                            return;
                        }
                        weightFox += field_value;
                        if (field_value < KILOGRAMS_OF_FOOD) {
                            weightFox -= KILOGRAMS_OF_FOOD - field_value;
                            return;
                        }
                        return;
                    } else {
                        weightFox -= KILOGRAMS_OF_FOOD;
                        return;
                    }
                }
            }
        }
        weightFox -= KILOGRAMS_OF_FOOD;
    }

    @Override
    public boolean checkTypeAnimal(Object o) {
        if (o instanceof Rabbit) {
            return checkAbleToEat(CHANCE_EAT_RABBIT);
        } else if (o instanceof Mouse) {
            return checkAbleToEat(CHANCE_EAT_MOUSE);
        } else if (o instanceof Duck) {
            return checkAbleToEat(CHANCE_EAT_DUCK);
        } else if (o instanceof Caterpillar) {
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
        int count = 0;
        int check = 0;

        int weight = WEIGHT_FOX_FINAL / 2 ;
        if(getWeightFox() <= weight)return;

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
            if (o instanceof Fox) {
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
                        if (repeat > objects.size()) return;
                        if (repeat == objects.size()) {
                            if (o instanceof Fox) {
                                while (true) {
                                    if (j + 1 >= objectArrayList.size()) {
                                        return;
                                    }
                                    Object o2 = objectArrayList.get(j + 1);
                                    j++;
                                    if (o2 instanceof Fox) {
                                        if (!checkTypeAnimalOnLocation(objectArrayList)) return;
                                        objectArrayList.add(new Fox());
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
                if (o instanceof Wolf) {
                    while (true) {
                        if (j + 1 >= objectArrayList.size()) {
                            return;
                        }
                        Object o2 = objectArrayList.get(j + 1);
                        if (o2 instanceof Fox) {
                            if (!checkTypeAnimalOnLocation(objectArrayList)) return;
                            objectArrayList.add(new Fox());
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

