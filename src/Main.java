import services.init.InitializationIsland;
import services.init.ListTypeAnimals;
import services.parameters.*;
import services.visual.Visual;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ListTypeAnimals listTypeAnimals = new ListTypeAnimals();
        InitializationIsland island = new InitializationIsland();
        listTypeAnimals.set();
        Settings settings = new Settings(10, 5, 15);
        try {
            island.addList(settings.number_animals_in_one_field);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 100; i++) {
                executorService.execute(new Action());
            }
        }
    }
}

