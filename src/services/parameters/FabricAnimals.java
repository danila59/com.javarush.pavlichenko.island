package services.parameters;

import entity.animal.Animal;
import entity.animal.herbivoreAnimal.*;
import entity.animal.predatorAnimal.*;

public class FabricAnimals extends Animal {
    public Animal createAnimal(AnimalType animalType) {
        Animal animal = null;
        switch (animalType) {
            case WOLF -> animal = new Wolf();
            case BEAR -> animal = new Bear();
            case FOX -> animal = new Fox();
            case EAGLE -> animal = new Eagle();
            case SNAKE -> animal = new Snake();
            case RABBIT -> animal = new Rabbit();
            case CATERPILLAR -> animal = new Caterpillar();
            case HOG -> animal = new Hog();
            case DEER -> animal = new Deer();
            case SHEEP -> animal = new Sheep();
            case MOUSE -> animal = new Mouse();
            case DUCK -> animal = new Duck();
            case GOAT -> animal = new Goat();
            case HORSE -> animal = new Horse();
            case BUFFALO -> animal = new Buffalo();
        }
        return animal;
    }
}

