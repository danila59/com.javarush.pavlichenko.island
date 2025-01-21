package services.parameters;

import entity.animal.plant.FabricPlants;
import entity.animal.plant.Grass;
import entity.animal.plant.PlantType;
import services.init.InitializationIsland;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AddGrass implements Runnable{
    FabricPlants fabricPlants = new FabricPlants();
    InitializationIsland initializationIsland = new InitializationIsland();


    public void addGrassOnLocations() {
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[j].length; j++) {
                ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[i][j];
                PlantType plantType = PlantType.values()[ThreadLocalRandom.current().nextInt(PlantType.values().length)];
                Object plantType1 = fabricPlants.createPlant(plantType);
                int field = 0;
                try {
                    field = initializationIsland.findingMaxCountOnField(plantType1);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
                int result = ThreadLocalRandom.current().nextInt(1, field);
                int plantOnLocation = checkPlantsOnLocation(arrayList);
                if (result + plantOnLocation >= field) {
                    break;
                }
                int res = ThreadLocalRandom.current().nextInt(1, 100);
                if (res >= 50) {
                    for (int k = 0; k < result; k++) {
                        arrayList.add(fabricPlants.createPlant(plantType));
                    }
                }
            }
        }
    }

    public int checkPlantsOnLocation(ArrayList<Object> arrayList) {
        int count = 0;
        for (Object object : arrayList) {
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
