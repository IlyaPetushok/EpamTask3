package by.petushokilya.entity;

import by.petushokilya.util.GeneratorId;

public class Truck implements Runnable{
    private int idTruck;
    private int capacity;
    private boolean priority;
    private State state;

    public enum State {
        New, Runnable, Waiting, Terminate
    }

    {
        idTruck = GeneratorId.generatorId();
        state = State.New;
    }

    public Truck() {
    }

    public Truck(int capacity, boolean priority) {
        this.capacity = capacity;
        this.priority = priority;
    }

    public int getIdTruck() {
        return idTruck;
    }

    public void setIdTruck(int idTruck) {
        this.idTruck = idTruck;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void run() {

    }
}
