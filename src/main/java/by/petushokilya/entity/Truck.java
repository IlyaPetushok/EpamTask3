package by.petushokilya.entity;

import by.petushokilya.exception.CustomException;
import by.petushokilya.main.Main;
import by.petushokilya.util.GeneratorId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Truck implements Runnable {
    private static final Logger logger= LogManager.getLogger(Truck.class.getName());
    private Random random = new Random();
    private int idTruck;
    private int capacity;
    private int occupationCapacity;
    private boolean isLoad;
    private State state;

    public enum State {
        New, Runnable, Waiting, Terminate
    }

    {
        idTruck = GeneratorId.generatorIdTruck();
        isLoad = random.nextBoolean();
        state = State.New;
        logger.info("Truck number "+this.idTruck+", state "+this.state);
    }

    public Truck() {
    }

    public Truck(int capacity, int occupationCapacity) {
        this.capacity = capacity;
        this.occupationCapacity = occupationCapacity;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int isOccupationCapacity() {
        return occupationCapacity;
    }

    public void setOccupationCapacity(int occupationCapacity) {
        this.occupationCapacity = occupationCapacity;
    }

    @Override
    public void run() {
        Base base = Base.getInstance();
        Terminal terminal = null;
        try {
            terminal = base.getFreeTerminal(this);
            processLoadOrUnload(terminal, base);
        } catch (InterruptedException exception) {
            logger.error("Mistake method run"+exception);
        } finally {
            base.realizedTerminal(terminal);
        }
    }

    private void processLoadOrUnload(Terminal terminal, Base base) throws InterruptedException {
        this.state = State.Runnable;
        logger.info("Truck number"+this.idTruck+",state "+this.state);
        if (this.isLoad) {
            load(base);
        } else {
            unload(base);
        }
        this.state = State.Terminate;
    }

    private void load(Base base) throws InterruptedException {
        int quantityForLoad;
        quantityForLoad = this.capacity - this.occupationCapacity;
        base.getQuantityProductOnBase().getAndAdd(-quantityForLoad);
        logger.info("Truck number "+this.idTruck+" come to load.");
        TimeUnit.SECONDS.sleep(3);
        this.state = State.Terminate;
        logger.info("Truck "+this.idTruck+",state "+this.state);
    }

    private void unload(Base base) throws InterruptedException {
        base.getQuantityProductOnBase().getAndAdd(this.occupationCapacity);
        logger.info("Truck number "+this.idTruck+" come to unload.");
        TimeUnit.SECONDS.sleep(3);
        this.state = State.Terminate;
        logger.info("Truck "+this.idTruck+",state "+this.state);
    }
}
