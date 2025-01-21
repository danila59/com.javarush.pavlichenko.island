package entity.visual;

import animal.herbivoreAnimal.*;
import animal.predatorAnimal.*;
import entity.parameters.Location;
import entity.parameters.Settings;
import plant.Grass;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Visual {

    public String[][] strings = new String[Settings.columnsCount][Settings.rowsCount];
    public TreeMap<Integer, String> treeMap = new TreeMap<>();

    public final String GRASS = "\uD83C\uDF40";

    public final String FOX = "\ud83e\udd8a";
    public final String EAGLE = "\ud83e\udd85";
    public final String SNAKE = "\ud83d\udc0d";
    public final String BEAR = "\ud83d\udc3b";
    public final String WOLF = "\ud83d\udc3a";

    public final String BUFFALO = "\ud83d\udc03";
    public final String HOG = "\ud83d\udc17";
    public final String DEER = "\ud83e\udd8c";
    public final String HORSE = "\ud83d\udc0e";
    public final String SHEEP = "\ud83d\udc11";
    public final String GOAT = "\ud83d\udc10";
    public final String RABBIT = "\ud83d\udc07";
    public final String DUCK = "\ud83e\udd86";
    public final String MOUSE = "\ud83d\udc01";
    public final String CATERPILLAR = "\ud83d\udc1b";

    public int numbers_fox;
    public int numbers_eagle;
    public int numbers_snake;
    public int numbers_bear;
    public int numbers_wolf;

    public int numbers_buffalo;
    public int numbers_hog;
    public int numbers_deer;
    public int numbers_horse;
    public int numbers_sheep;
    public int numbers_goat;
    public int numbers_rabbit;
    public int numbers_duck;
    public int numbers_mouse;
    public int numbers_caterpillar;

    public int numbers_grass;


    public void takeList() {
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[j].length; j++) {
                ArrayList<Object> arrayList1 = (ArrayList<Object>) Location.LOCATION_ISLAND[i][j];
                for (int i1 = 0; i1 < arrayList1.size(); i1++) {
                    Object animal = arrayList1.get(i1);
                    recognizeTypeAnimal(animal);
                }
                initTreeMap();
                setValue(i, j, sortedTreeMap());
                restart();
                restartTreeMap();
            }
        }
    }

    public void recognizeTypeAnimal(Object o) {
        if (o instanceof Fox) {
            numbers_fox++;
        } else if (o instanceof Wolf) {
            numbers_wolf++;
        } else if (o instanceof Bear) {
            numbers_bear++;
        } else if (o instanceof Eagle) {
            numbers_eagle++;
        } else if (o instanceof Snake) {
            numbers_snake++;
        } else if (o instanceof Buffalo) {
            numbers_buffalo++;
        } else if (o instanceof Hog) {
            numbers_hog++;
        } else if (o instanceof Deer) {
            numbers_deer++;
        } else if (o instanceof Goat) {
            numbers_goat++;
        } else if (o instanceof Sheep) {
            numbers_sheep++;
        } else if (o instanceof Horse) {
            numbers_horse++;
        } else if (o instanceof Rabbit) {
            numbers_rabbit++;
        } else if (o instanceof Duck) {
            numbers_duck++;
        } else if (o instanceof Mouse) {
            numbers_mouse++;
        } else if (o instanceof Caterpillar) {
            numbers_caterpillar++;
        } else if (o instanceof Grass) {
            numbers_grass++;
        }
    }

    public void initTreeMap() {
        treeMap.put(numbers_fox, FOX);
        treeMap.put(numbers_wolf, WOLF);
        treeMap.put(numbers_bear, BEAR);
        treeMap.put(numbers_eagle, EAGLE);
        treeMap.put(numbers_snake, SNAKE);
        treeMap.put(numbers_buffalo, BUFFALO);
        treeMap.put(numbers_hog, HOG);
        treeMap.put(numbers_deer, DEER);
        treeMap.put(numbers_goat, GOAT);
        treeMap.put(numbers_sheep, SHEEP);
        treeMap.put(numbers_horse, HORSE);
        treeMap.put(numbers_rabbit, RABBIT);
        treeMap.put(numbers_duck, DUCK);
        treeMap.put(numbers_mouse, MOUSE);
        treeMap.put(numbers_caterpillar, CATERPILLAR);
        treeMap.put(numbers_grass, GRASS);
    }

    public String sortedTreeMap() {
        int largestKey = 0;
        String largestValue = "";
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            int Key = entry.getKey();
            String val = entry.getValue();
            if (Key > largestKey) {
                largestKey = Key;
                largestValue = val;
            }
        }
        return largestValue + largestKey;
    }

    public void setValue(int i, int j, String value) {
        strings[i][j] = value;
    }

    public void restartTreeMap() {
        treeMap.clear();
    }

    public void printArray() {
        for (int i = 0; i < strings.length; i++) {
            System.out.print("[");
            for (int i1 = 0; i1 < strings[i1].length; i1++) {
                if(strings[i] == null || strings[i1] == null)continue;
                System.out.print(" |" + strings[i][i1] + "| ");
            }
            System.out.print("]\n");
        }
    }

    public void restart() {
        numbers_fox = 0;
        numbers_eagle = 0;
        numbers_snake = 0;
        numbers_bear = 0;
        numbers_wolf = 0;
        numbers_buffalo = 0;
        numbers_hog = 0;
        numbers_deer = 0;
        numbers_horse = 0;
        numbers_sheep = 0;
        numbers_goat = 0;
        numbers_rabbit = 0;
        numbers_duck = 0;
        numbers_mouse = 0;
        numbers_caterpillar = 0;
        numbers_grass = 0;
    }
}
