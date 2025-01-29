package services.parameters;

import entity.animal.Animal;
import entity.animal.herbivoreAnimal.*;
import entity.animal.predatorAnimal.*;
import services.visual.Visual;


import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.*;

public class Action implements Runnable {
    Statistics statistics = new Statistics();
    Visual visual = new Visual();
    public static Queue<Action> threadSafeQueue = new ConcurrentLinkedQueue<>();


    public void manipulationEat() {
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[j].length -1; j++) {
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
                    if (o instanceof Animal) {
                        ((Animal) o).eat(arrayList1);
                    }
                }
            }
        }
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
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[j].length-1; j++) {
                ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[i][j];
                int countListSize = arrayList.size();
                for (int k = 0; k < countListSize; k++) {
                    Object o = arrayList.get(k);
                    if (o instanceof Animal) {
                        ((Animal) o).multiply(arrayList, countListSize);
                    }
                }
            }
        }
    }

    public void manipulationMove() {
        for (int i = 0; i < Location.LOCATION_ISLAND.length; i++) {
            for (int j = 0; j < Location.LOCATION_ISLAND[i].length-1; j++) {
                ArrayList<Object> arrayList = (ArrayList<Object>) Location.LOCATION_ISLAND[i][j];
                int countListSize = arrayList.size();
                for (int k = 0; k < arrayList.size(); k++) {
                    if (countListSize > arrayList.size()) {
                        k -= 1;
                        countListSize--;
                    }
                    Object o = arrayList.get(k);
                    if (o instanceof Animal) {
                        ((Animal) o).move(o,arrayList, i, j, Location.LOCATION_ISLAND[i].length,  0);
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
            executorService.scheduleAtFixedRate(new AddGrass(), 0, 1, TimeUnit.SECONDS);
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
