import entity.init.InitializationIsland;
import entity.init.ListTypeAnimals;
import entity.parameters.*;
import entity.visual.Visual;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class Main {

    public static void main(String[] args) {

        ListTypeAnimals listTypeAnimals = new ListTypeAnimals();
        Statistics statistics = new Statistics();
        InitializationIsland island = new InitializationIsland();
        Action action = new Action();
        Visual visual = new Visual();

        listTypeAnimals.set();
        Settings settings = new Settings(10, 5, 15);


        try {
            island.addList(settings.number_animals_in_one_field);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try (ExecutorService executorService = newSingleThreadExecutor()) {
            for (int i = 0; i < 100; i++) {
                executorService.execute(new Action());

            }
//            executorService.submit(new Action());
//        boolean b = true;
//        while (b) {
//            action.manipulationEat();
//            action.manipulationMultiply();
//            action.manipulationMove();
//            action.addGrassOnLocations();
//            visual.takeList();
//            visual.printArray();
//            statistics.statisticIsland();
//            statistics.numberAnimalsOnIsland();
//            statistics.numberPlantsOnIsland();
//            statistics.numberPredatoryAnimals();
//            statistics.numberHerbivoresAnimals();
//            System.out.println(statistics.toString());
//            b = statistics.endGame();
//            statistics.restartField();
//            System.out.println();
//        }
        }
    }
}

