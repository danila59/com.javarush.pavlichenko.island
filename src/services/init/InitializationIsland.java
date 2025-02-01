package services.init;

import entity.animal.Animal;
import services.parameters.AnimalType;
import services.parameters.FabricAnimals;
import services.parameters.Location;
import entity.animal.plant.FabricPlants;
import entity.animal.plant.PlantType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class InitializationIsland {

    FabricAnimals fabricAnimals = new FabricAnimals();
    FabricPlants fabricPlants = new FabricPlants();

    public void addList(int countAnimalsOnField) { // тут нужно присваивать ячейкам заполненные листы
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[i].length; j++) {
                Location.LOCATION_ISLAND[i][j] = addInList(countAnimalsOnField);
            }
        }
    }

    public ArrayList<Animal> addInList(int countAnimalsOnField)  {// тут будут заполняться листы рандомными животными + рандом количество
        ArrayList<Animal> animalArrayList = new ArrayList<>();
        for (int i = 0; i < countAnimalsOnField; i++) {
            Random random = new Random();
            AnimalType animalType = AnimalType.values()[random.nextInt(AnimalType.values().length)];
            Animal animal = fabricAnimals.createAnimal(animalType);
            boolean isExistInList = checkCountAnimalsInList(animalArrayList, animal);
            if (!isExistInList) {
                i -= 1;
            }
            int findingCountOnField = animal.getMaxCountOnField();
            int randomCountAnimals = new Random().nextInt(1, findingCountOnField);
            if (isExistInList) {
                while (randomCountAnimals != 0) {
                    animalArrayList.add(fabricAnimals.createAnimal(animalType));
                    randomCountAnimals--;
                }
            }
        }
        return addInListPlants(animalArrayList);
    }

    public ArrayList<Animal> addInListPlants(ArrayList<Animal> arrayList)  {
        Random random = new Random();
        PlantType plantType = PlantType.values()[random.nextInt(PlantType.values().length)];
        Animal plantType1 = fabricPlants.createPlant(plantType);
        int field = plantType1.getMaxCountOnField();
        int result = new Random().nextInt(1, field) / 2 ;
            for (int i = 0; i < result; i++) {
                arrayList.add(fabricPlants.createPlant(plantType));
            }

        shuffleList(arrayList);
        return arrayList;
    }

    public boolean checkCountAnimalsInList(ArrayList<Animal> animalArrayList, Animal animal) {
        if (animalArrayList.isEmpty()) return true;
        for (Animal animal1 : animalArrayList) {
            if (animal.getClass().getSimpleName().equals(animal1.getClass().getSimpleName())) {
                return false;
            }
        }
        return true;
    }

    public void shuffleList(ArrayList<Animal> arrayList) {
        Collections.shuffle(arrayList);
    }
}
