package entity.animal.plant;

import entity.animal.Animal;

public class FabricPlants {


    public Plant createPlant(PlantType plantType){
       Plant plant = null;

        switch (plantType){
            case GRASS -> plant = new Grass();
        }
        return plant;
    }
}
