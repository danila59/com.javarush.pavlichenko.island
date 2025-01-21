package services.init;

import entity.animal.herbivoreAnimal.*;
import entity.animal.predatorAnimal.*;
import entity.animal.plant.Grass;
import entity.animal.plant.Plant;

import java.util.ArrayList;

public class ListTypeAnimals {
    public static final ArrayList<Herbivore> HERBIVORE_ARRAY_LIST = new ArrayList<>();
    public static final ArrayList<Predator> PREDATOR_ARRAY_LIST = new ArrayList<>();
    public static final ArrayList<Plant> PLANT_ARRAY_LIST = new ArrayList<>();

    public void set() {
        HERBIVORE_ARRAY_LIST.add(new Buffalo());
        HERBIVORE_ARRAY_LIST.add(new Caterpillar());
        HERBIVORE_ARRAY_LIST.add(new Deer());
        HERBIVORE_ARRAY_LIST.add(new Duck());
        HERBIVORE_ARRAY_LIST.add(new Goat());
        HERBIVORE_ARRAY_LIST.add(new Horse());
        HERBIVORE_ARRAY_LIST.add(new Mouse());
        HERBIVORE_ARRAY_LIST.add(new Rabbit());
        HERBIVORE_ARRAY_LIST.add(new Sheep());
        HERBIVORE_ARRAY_LIST.add(new Hog());

        PREDATOR_ARRAY_LIST.add(new Wolf());
        PREDATOR_ARRAY_LIST.add(new Bear());
        PREDATOR_ARRAY_LIST.add(new Fox());
        PREDATOR_ARRAY_LIST.add(new Snake());
        PREDATOR_ARRAY_LIST.add(new Eagle());

        PLANT_ARRAY_LIST.add(new Grass());
    }
}
