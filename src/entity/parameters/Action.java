package entity.parameters;

import animal.herbivoreAnimal.*;
import animal.predatorAnimal.*;
import entity.init.InitializationIsland;
import entity.visual.Visual;
import plant.FabricPlants;
import plant.Grass;
import plant.PlantType;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Action implements Runnable {
    ReentrantLock lock = new ReentrantLock();
    Statistics statistics = new Statistics();
    Visual visual = new Visual();
    FabricPlants fabricPlants = new FabricPlants();
    InitializationIsland initializationIsland = new InitializationIsland();
    public static Queue<Action> threadSafeQueue = new ConcurrentLinkedQueue<>();


    public void manipulationEat() {
        lock.lock();
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[j].length; j++) {
                ArrayList<Object> arrayList1 = (ArrayList<Object>) Location.LOCATION_ISLAND[i][j];
                ArrayList<Object> arrayListDuplicate = new ArrayList<>(arrayList1);
                ArrayList<Object> arrayListRepeat = new ArrayList<>();
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
                    Object o = null;
                    if (arrayList1.isEmpty()) break;
                    if (k >= arrayList1.size()) {
                        if (!repeatLast(arrayListRepeat, arrayList1.get(k - 1))) {
                            o = arrayList1.get(k - 1);
                        } else {
                            break;
                        }
                    } else {
                        o = arrayList1.get(k);
                        arrayListRepeat.add(o);
                    }

                    if (o instanceof Wolf) {
                        ((Wolf) o).eat(arrayList1);
                    }
                    if (o instanceof Bear) {
                        ((Bear) o).eat(arrayList1);
                    }
                    if (o instanceof Fox) {
                        ((Fox) o).eat(arrayList1);
                    }
                    if (o instanceof Snake) {
                        ((Snake) o).eat(arrayList1);
                    }
                    if (o instanceof Eagle) {
                        ((Eagle) o).eat(arrayList1);
                    }
                    if (o instanceof Deer) {
                        ((Deer) o).eat(arrayList1);
                    }
                    if (o instanceof Buffalo) {
                        ((Buffalo) o).eat(arrayList1);
                    }
                    if (o instanceof Duck) {
                        ((Duck) o).eat(arrayList1);
                    }
                    if (o instanceof Goat) {
                        ((Goat) o).eat(arrayList1);
                    }
                    if (o instanceof Hog) {
                        ((Hog) o).eat(arrayList1);
                    }
                    if (o instanceof Rabbit) {
                        ((Rabbit) o).eat(arrayList1);
                    }
                    if (o instanceof Mouse) {
                        ((Mouse) o).eat(arrayList1);
                    }
                    if (o instanceof Sheep) {
                        ((Sheep) o).eat(arrayList1);
                    }
                    if (o instanceof Horse) {
                        ((Horse) o).eat(arrayList1);
                    }
                }
            }
        }
        lock.unlock();
        manipulationMultiply();
    }

    public boolean checkRepeatCount(ArrayList<Object> arrayList1, ArrayList<Object> arrayListDuplicate, int index) {
        for (int i = 0; i < arrayListDuplicate.size(); i++) {
            Object o1 = arrayListDuplicate.get(i);
            boolean b = true;
            while (b) {
                for (int j = 0; j < arrayList1.size(); j++) {
                    Object o2 = arrayList1.get(j);
                    if (o1 == o2) {
                        b = false;
                    }
                    if (j == arrayList1.size() - 1 && b == true) {
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

    public boolean repeatLast(ArrayList<Object> arrayListRepeat, Object o) {
        for (int i = 0; i < arrayListRepeat.size(); i++) {
            if (o == arrayListRepeat.get(i)) {
                return true;
            }
        }
        return false;
    }


    public void manipulationMultiply() {
        lock.lock();
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[j].length; j++) {
                ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[i][j];
                int countListSize = arrayList.size();
                for (int k = 0; k < countListSize; k++) {
                    Object o = arrayList.get(k);
                    if (o instanceof Wolf) {
                        ((Wolf) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Bear) {
                        ((Bear) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Fox) {
                        ((Fox) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Snake) {
                        ((Snake) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Eagle) {
                        ((Eagle) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Deer) {
                        ((Deer) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Buffalo) {
                        ((Buffalo) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Caterpillar) {
                        ((Caterpillar) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Duck) {
                        ((Duck) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Goat) {
                        ((Goat) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Hog) {
                        ((Hog) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Rabbit) {
                        ((Rabbit) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Mouse) {
                        ((Mouse) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Sheep) {
                        ((Sheep) o).multiply(arrayList, countListSize);
                    }
                    if (o instanceof Horse) {
                        ((Horse) o).multiply(arrayList, countListSize);
                    }
                }
            }
        }
        lock.unlock();
        manipulationMove();
    }

    public void manipulationMove() {
        lock.lock();
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[i].length; j++) {
                ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[i][j];
                int countListSize = arrayList.size();
                for (int k = 0; k < arrayList.size(); k++) {
                    if (countListSize > arrayList.size()) {
                        k -= 1;
                        countListSize--;
                    }
                    Object o = arrayList.get(k);
                    if (o instanceof Wolf) {
                        ((Wolf) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Bear) {
                        ((Bear) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Fox) {
                        ((Fox) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Snake) {
                        ((Snake) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Eagle) {
                        ((Eagle) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Deer) {
                        ((Deer) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Buffalo) {
                        ((Buffalo) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Duck) {
                        ((Duck) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Goat) {
                        ((Goat) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Hog) {
                        ((Hog) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Rabbit) {
                        ((Rabbit) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Mouse) {
                        ((Mouse) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Sheep) {
                        ((Sheep) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                    if (o instanceof Horse) {
                        ((Horse) o).move(o, arrayList, i, j, Location.LOCATION_ISLAND[i].length);
                    }
                }
            }
        }
        lock.unlock();
        addGrassOnLocations();
    }

    public void addGrassOnLocations() {
        lock.lock();
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
        lock.unlock();
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
        manipulationEat();
//        System.out.println("День № " + Statistics.number_days ++);
//        System.out.println(  Thread.currentThread().getName());
//            manipulationMultiply();
//            manipulationMove();
//            addGrassOnLocations();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//            visual.takeList();
//            visual.printArray();
//            statistics.statisticIsland();
//            statistics.numberAnimalsOnIsland();
//            statistics.numberPlantsOnIsland();
//            statistics.numberPredatoryAnimals();
//            statistics.numberHerbivoresAnimals();
//            System.out.println(statistics.toString());
//           b=  statistics.endGame();
//
//            statistics.restartField();
//            System.out.println();
    }
}





