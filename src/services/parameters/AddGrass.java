package services.parameters;

import entity.animal.Animal;
import entity.animal.plant.FabricPlants;
import entity.animal.plant.Grass;
import entity.animal.plant.PlantType;
import services.init.InitializationIsland;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AddGrass implements Runnable {
    FabricPlants fabricPlants = new FabricPlants();
    InitializationIsland initializationIsland = new InitializationIsland();


    public void addGrassOnLocations() {
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[j].length; j++) {
                ArrayList<Animal> arrayList = Location.LOCATION_ISLAND[i][j];
                PlantType plantType = PlantType.values()[ThreadLocalRandom.current().nextInt(PlantType.values().length)];
               Animal plantType1 = fabricPlants.createPlant(plantType);
                int field = plantType1.getMaxCountOnField();

                int result = ThreadLocalRandom.current().nextInt(1, field);
                int plantOnLocation = checkPlantsOnLocation(arrayList);
                if (result + plantOnLocation >= field) {
                    break;
                }
                int res = ThreadLocalRandom.current().nextInt(1, 100);
                if (res >= 30) {
                    for (int k = 0; k < result; k++) {
                        arrayList.add(fabricPlants.createPlant(plantType));
                    }
                }
            }
        }
    }

    public int checkPlantsOnLocation(ArrayList<Animal> arrayList) {
        int count = 0;
        for (Animal object : arrayList) {
            if (object instanceof Grass) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void run() {
        addGrassOnLocations();
    }
}
