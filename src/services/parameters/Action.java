package services.parameters;

import entity.animal.Animal;
import entity.animal.herbivoreAnimal.Caterpillar;
import entity.animal.plant.Plant;
import services.init.InitializationIsland;
import services.visual.Visual;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.*;

public class Action implements Runnable {
    Statistics statistics = new Statistics();
    Visual visual = new Visual();
    InitializationIsland island = new InitializationIsland();
    public static Queue<Action> threadSafeQueue = new ConcurrentLinkedQueue<>();



    public void manipulationEat() {
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j <= Location.LOCATION_ISLAND[j].length -1; j++) {
                ArrayList<Animal> arrayList1 =  Location.LOCATION_ISLAND[i][j];
                ArrayList<Animal> arrayListDuplicate = new ArrayList<>(arrayList1);
                ArrayList<Animal> arrayListRepeat = new ArrayList<>();
                int countListSize = arrayList1.size();
                for (int k = 0; k <= arrayList1.size(); k++) {
                    if (k > 0 && arrayList1.size() < countListSize && k < arrayList1.size()) {
                        if (!checkRepeatCount(arrayList1, arrayListDuplicate, k)) {
                            if (arrayList1.size() == arrayListDuplicate.size()) {
                                k -= 1;
                                countListSize--;
                            } else {
                                k -= 1;
                                countListSize--;
                            }
                        }
                    }
                    Animal animal;
                    if (arrayList1.isEmpty()) break;
                    if (k >= arrayList1.size()) {
                        if (!repeatLast(arrayListRepeat, arrayList1.get(k - 1))) {
                            animal = arrayList1.get(k - 1);
                        } else {
                            break;
                        }
                    } else {
                        animal = arrayList1.get(k);
                        arrayListRepeat.add(animal);
                    }
                    if (animal instanceof Animal) {
                        if(animal instanceof Plant || animal instanceof Caterpillar)continue;
                        animal.eat(arrayList1,animal);
                    }
                }
            }
        }
    }

    public boolean checkRepeatCount(ArrayList<Animal> arrayList1, ArrayList<Animal> arrayListDuplicate, int index) {
        for (int i = 0; i < arrayListDuplicate.size(); i++) {
            Animal o1 = arrayListDuplicate.get(i);
            boolean b = true;
            while (b) {
                for (int j = 0; j < arrayList1.size(); j++) {
                   Animal o2 = arrayList1.get(j);
                    if (o1 == o2) {
                        b = false;
                    }
                    if (j == arrayList1.size() - 1 && b) {
                        if (i < index) {
                            arrayListDuplicate.remove(o1);
                            return false;
                        } else {
                            arrayListDuplicate.remove(o1);
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean repeatLast(ArrayList<Animal> arrayListRepeat, Object o) {
        for (int i = 0; i < arrayListRepeat.size(); i++) {
            if (o == arrayListRepeat.get(i)) {
                return true;
            }
        }
        return false;
    }


    public void manipulationMultiply() {
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {// нужно дать знание, что этот тип животного был обработан
            for (int j = 0; j <= Location.LOCATION_ISLAND[j].length-1 ; j++) {
                ArrayList<Animal> arrayList =  Location.LOCATION_ISLAND[i][j];
                ArrayList<Animal> objectArrayList = new ArrayList<>();
                int countListSize = arrayList.size();
                for (int k = 0; k < countListSize; k++) {
                    Animal animal = arrayList.get(k);
                    if (island.checkCountAnimalsInList(objectArrayList, animal)) {
                        objectArrayList.add(animal);
                        if (animal instanceof Animal) {
                            if (animal instanceof Plant|| animal instanceof Caterpillar)continue;
                            int count = checkAnimalOnLocation(arrayList ,animal);
                            animal.multiply(arrayList, count, animal);
                        }
                    }
                }
            }
        }
    }

    public int checkAnimalOnLocation(ArrayList<Animal> arrayList, Animal animal) {
        int count = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            Animal animal1 = arrayList.get(i);
            if (animal.getClass().getSimpleName().equals(animal1.getClass().getSimpleName())) {
                count++;
            }
        }
        return count;
    }

    public void manipulationMove() {
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j <= Location.LOCATION_ISLAND[i].length - 1; j++) {
                ArrayList<Animal> arrayList =  Location.LOCATION_ISLAND[i][j];
                int countListSize = arrayList.size();
                for (int k = 0; k < arrayList.size(); k++) {
                    if (countListSize > arrayList.size()) {
                        k -= 1;
                        countListSize--;
                    }
                    Animal animal = arrayList.get(k);
                    if (animal instanceof Animal) {
                        if(animal instanceof Plant || animal instanceof Caterpillar)continue;
                         animal.move(animal, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        synchronized (Action.class) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("День №" + Statistics.number_days);
            manipulationEat();
            manipulationMultiply();
            manipulationMove();
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(new AddGrass(), 0, 3, TimeUnit.SECONDS);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            visual.takeList();
            visual.printArray();
            statistics.statisticIsland();
            statistics.numberAnimalsOnIsland();
            statistics.numberPlantsOnIsland();
            statistics.numberPredatoryAnimals();
            statistics.numberHerbivoresAnimals();
            System.out.println(statistics.toString());
            statistics.endGame();
            statistics.restartField();
            System.out.println();
        }
    }
}
