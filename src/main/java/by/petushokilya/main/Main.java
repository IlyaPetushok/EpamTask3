package by.petushokilya.main;

import by.petushokilya.entity.Truck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Logger logger= LogManager.getLogger(Main.class.getName());
        logger.info("Start Thread");
        Thread thread=new Thread(new Truck());
        thread.start();
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            executorService.submit(new Truck());
        }
    }
}
