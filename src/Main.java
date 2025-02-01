import services.init.InitializationIsland;
import services.init.ListTypeAnimals;
import services.parameters.*;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ListTypeAnimals listTypeAnimals = new ListTypeAnimals();// разобраться с null + уменьшить рождаемость
        InitializationIsland island = new InitializationIsland();
        listTypeAnimals.set();
        Settings settings = new Settings(100, 20, 5, 100);
        island.addList(settings.number_animals_in_one_field);
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < settings.daysIsland; i++) {
                executorService.execute(new Action());
            }
        }
    }
}

