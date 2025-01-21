package entity.animal.plant;

public class FabricPlants {


    public Plant createPlant(PlantType plantType){
       Plant plant = null;

        switch (plantType){
            case GRASS -> plant = new Grass();
        }
        return plant;
    }
}
