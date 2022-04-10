package by.petushokilya.main;

import by.petushokilya.creator.CreatorTruck;
import by.petushokilya.entity.Truck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final int QUANTITY=7;
    public static void main(String[] args) {
        Logger logger= LogManager.getLogger(Main.class.getName());
        logger.info("Start Thread");
        CreatorTruck creatorTruck=CreatorTruck.getInstance();
        List<Truck> listTrucks=creatorTruck.creatorTrucks(QUANTITY);
        ExecutorService executorService= Executors.newFixedThreadPool(QUANTITY);
        for(int i=0;i<QUANTITY;i++){
            executorService.execute(listTrucks.get(i));
        }
        executorService.shutdown();
    }
}
