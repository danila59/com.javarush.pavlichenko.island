package services.init;

import services.parameters.AnimalType;
import services.parameters.FabricAnimals;
import services.parameters.Location;
import entity.animal.plant.FabricPlants;
import entity.animal.plant.PlantType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class InitializationIsland {

    FabricAnimals fabricAnimals = new FabricAnimals();
    FabricPlants fabricPlants = new FabricPlants();

    public void addList(int countAnimalsOnField) throws NoSuchFieldException { // тут нужно присваивать ячейкам заполненные листы
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[i].length; j++) {
                Location.LOCATION_ISLAND[i][j] = addInList(countAnimalsOnField);
            }
        }
    }

    public ArrayList<Object> addInList(int countAnimalsOnField) throws NoSuchFieldException {// тут будут заполняться листы рандомными животными + рандом количество
        ArrayList<Object> objectArrayList = new ArrayList<>();
        for (int i = 0; i < countAnimalsOnField; i++) {
            Random random = new Random();
            AnimalType animalType = AnimalType.values()[random.nextInt(AnimalType.values().length)];
            Object objectAnimal = fabricAnimals.createAnimal(animalType);
            boolean b = checkCountAnimalsInList(objectArrayList, objectAnimal);
            if (!b) {
                i -= 1;
            }
            int findingCountOnField = findingMaxCountOnField(objectAnimal);
            int randomCountAnimals = new Random().nextInt(1, findingCountOnField);
            if (b) {
                while (randomCountAnimals != 0) {
                    objectArrayList.add(fabricAnimals.createAnimal(animalType));
                    randomCountAnimals--;
                }
            }
        }
        return addInListPlants(objectArrayList);
    }

    public ArrayList<Object> addInListPlants(ArrayList<Object> arrayList) throws NoSuchFieldException {
        Random random = new Random();
        PlantType plantType = PlantType.values()[random.nextInt(PlantType.values().length)];
        Object plantType1 = fabricPlants.createPlant(plantType);
        int field = findingMaxCountOnField(plantType1);
        int result = new Random().nextInt(1, field);
            for (int i = 0; i < result; i++) {
                arrayList.add(fabricPlants.createPlant(plantType));
            }

        shuffleList(arrayList);
        return arrayList;
    }

    public int findingMaxCountOnField(Object o) throws NoSuchFieldException {
        int field_value;
        Field field = o.getClass().getDeclaredField("MAX_COUNT_ON_FIELD");
        field.setAccessible(true);
        try {
            field_value = (int) field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return field_value;
    }

    public boolean checkCountAnimalsInList(ArrayList<Object> objectArrayList, Object animal) {
        if (objectArrayList.isEmpty()) {
            return true;
        }
        String s = animal.getClass().getSimpleName();
        for (int i = 0; i < objectArrayList.size(); i++) {
            String f = objectArrayList.get(i).getClass().getSimpleName();
            if (s.equals(f)) {
                return false;
            }
        }
        return true;
    }

    public void shuffleList(ArrayList<Object> arrayList) {
        Collections.shuffle(arrayList);
    }
}
