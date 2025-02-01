package services.parameters;

import entity.animal.Animal;
import services.init.ListTypeAnimals;

import java.util.ArrayList;

public class Statistics {

    public static int number_of_animals_born ;

    public static int the_number_animals_killed_starvation ;

    public static int number_animals_eaten ;

    public static int total_number_elements_on_island;

    public static int amount_of_grass_eaten ;

    public static int number_Plants_On_Island ;

    public static int only_the_number_animals ;

    public static int number_predatory_animals ;

    public static int number_Herbivores_Animals ;

    public static int number_days = 1 ;


    public void statisticIsland() {
        total_number_elements_on_island = 0;
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j <= Location.LOCATION_ISLAND[j].length-1; j++) {
                ArrayList<Animal> arrayList1 =  Location.LOCATION_ISLAND[i][j];
                total_number_elements_on_island += arrayList1.size();
            }
        }
    }

    public void numberAnimalsOnIsland() {
        only_the_number_animals = 0;
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j <= Location.LOCATION_ISLAND[j].length-1; j++) {
                ArrayList<Animal> arrayList1 =  Location.LOCATION_ISLAND[i][j];
                for (int i1 = 0; i1 < arrayList1.size(); i1++) {
                    String nameClass = arrayList1.get(i1).getClass().getSimpleName();
                    for (int i2 = 0; i2 < ListTypeAnimals.HERBIVORE_ARRAY_LIST.size(); i2++) {
                        String nameClass1 = ListTypeAnimals.HERBIVORE_ARRAY_LIST.get(i2).getClass().getSimpleName();
                        if (nameClass.equals(nameClass1)) {
                            only_the_number_animals++;
                            break;
                        }
                    }
                    for (int i3 = 0; i3 < ListTypeAnimals.PREDATOR_ARRAY_LIST.size(); i3++) {
                        String nameClass2 = ListTypeAnimals.PREDATOR_ARRAY_LIST.get(i3).getClass().getSimpleName();
                        if (nameClass.equals(nameClass2)) {
                            only_the_number_animals++;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Number of animals on the island = " + only_the_number_animals);
    }

    public void numberPlantsOnIsland() {
        number_Plants_On_Island = 0;
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j <= Location.LOCATION_ISLAND[j].length-1; j++) {
                ArrayList<Animal> arrayList1 =  Location.LOCATION_ISLAND[i][j];
                for (int i1 = 0; i1 < arrayList1.size(); i1++) {
                    String nameClass = arrayList1.get(i1).getClass().getSimpleName();
                    for (int i2 = 0; i2 < ListTypeAnimals.PLANT_ARRAY_LIST.size(); i2++) {
                        String nameClass1 = ListTypeAnimals.PLANT_ARRAY_LIST.get(i2).getClass().getSimpleName();
                        if (nameClass.equals(nameClass1)) {
                            number_Plants_On_Island++;
                        }
                    }
                }
            }
        }
        System.out.println("Number of plants on the island = " + number_Plants_On_Island);
    }

    public void restartField() {
        amount_of_grass_eaten = 0;
        number_of_animals_born = 0;
        the_number_animals_killed_starvation = 0;
        number_animals_eaten = 0;
        number_Herbivores_Animals = 0;
        number_predatory_animals = 0;
        number_days++;
    }

    public void numberPredatoryAnimals() {
        number_predatory_animals = 0;
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j <= Location.LOCATION_ISLAND[j].length-1; j++) {
                ArrayList<Animal> arrayList1 =  Location.LOCATION_ISLAND[i][j];
                for (int i1 = 0; i1 < arrayList1.size(); i1++) {
                    String nameClass = arrayList1.get(i1).getClass().getSimpleName();
                    for (int i2 = 0; i2 < ListTypeAnimals.PREDATOR_ARRAY_LIST.size(); i2++) {
                        String nameClass2 = ListTypeAnimals.PREDATOR_ARRAY_LIST.get(i2).getClass().getSimpleName();
                        if (nameClass.equals(nameClass2)) {
                            number_predatory_animals++;
                        }
                    }
                }
            }
        }
    }

    public void numberHerbivoresAnimals() {
        number_Herbivores_Animals = 0;
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j <= Location.LOCATION_ISLAND[j].length-1; j++) {
                ArrayList<Animal> arrayList1 =  Location.LOCATION_ISLAND[i][j];
                for (int i1 = 0; i1 < arrayList1.size(); i1++) {
                    String nameClass = arrayList1.get(i1).getClass().getSimpleName();
                    for (int i2 = 0; i2 < ListTypeAnimals.HERBIVORE_ARRAY_LIST.size(); i2++) {
                        String nameClass1 = ListTypeAnimals.HERBIVORE_ARRAY_LIST.get(i2).getClass().getSimpleName();
                        if (nameClass.equals(nameClass1)) {
                            number_Herbivores_Animals++;
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean endGame() {
        if (number_Plants_On_Island == 0) {
            System.out.println("На острове не осталось растений.");
            System.exit(0);
        } else if (number_predatory_animals == 0) {
            System.out.println("На острове не осталось хищных животных.");
            System.exit(0);
        } else if (number_Herbivores_Animals == 0) {
            System.out.println("На острове не осталось травоядных животных.");
            System.exit(0);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "amount_of_grass_eaten=" + amount_of_grass_eaten +
                ", number_of_animals_born=" + number_of_animals_born +
                ", the_number_animals_killed_starvation=" + the_number_animals_killed_starvation +
                ", number_animals_eaten=" + number_animals_eaten +
                ", total_number_elements_on_island=" + total_number_elements_on_island +
                ", number_predatory_animals=" + number_predatory_animals +
                ", number_Herbivores_Animals=" + number_Herbivores_Animals +
                '}';
    }
}
