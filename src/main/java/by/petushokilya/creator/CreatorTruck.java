package by.petushokilya.creator;

import by.petushokilya.entity.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatorTruck {
    private List<Truck> trucks;
    Random random = new Random();
    private static final int MAX_CAPACITY = 50;
    private static final int MIN_CAPACITY = 30;

    private static CreatorTruck instance;

    public static CreatorTruck getInstance() {
        if (instance == null) {
            instance = new CreatorTruck();
        }
        return instance;
    }

    private CreatorTruck() {
        trucks = new ArrayList<>();
    }

    public List<Truck> creatorTrucks(int quantity) {
        for (int i = 0; i < quantity; i++) {
            Truck truck = createProperties();
            trucks.add(truck);
        }
        return trucks;
    }

    private Truck createProperties() {
        boolean isLoad = false;
        int capacity = random.nextInt(MAX_CAPACITY - MIN_CAPACITY) + MIN_CAPACITY;
        int occupation = random.nextInt(capacity);
        if (capacity != occupation) {
            isLoad = random.nextBoolean();
        }
        return new Truck(capacity, occupation, isLoad);
    }
}
