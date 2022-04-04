package by.petushokilya.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Base {
    public static Base instance;
    private static final ReentrantLock lock=new ReentrantLock(true);
    private static final CountDownLatch initializingLatch =new CountDownLatch(1);
    private static final AtomicBoolean isInstanceInitialized=new AtomicBoolean(false);
    private static final int DEFAULT_TRUCK_CAPACITY=1000;
    private static final int FREE_PLACE_FOR_BASE=10;
    private final Deque<Terminal> freeTerminals;
    private Condition condition= lock.newCondition();


    public static Base getInstance(){
        if(instance==null){
            while (isInstanceInitialized.compareAndSet(false,true)){
             instance=new Base();
             initializingLatch.countDown();
            }
            try {
                initializingLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private Base(){
        freeTerminals=new ArrayDeque<>();
        for(int i=0;i<FREE_PLACE_FOR_BASE;i++){
            Terminal terminal=new Terminal();
            freeTerminals.add(terminal);
        }
    }

    public Terminal getFreeTerminal(Truck truck){
        Terminal terminal = null;
        truck.setState(Truck.State.Waiting);
        try {
            lock.lock();
            while(freeTerminals.size()==0){
               condition.await();
            }
            terminal=freeTerminals.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return terminal;
    }

    public void realizedTerminal(Terminal terminal){
        lock.lock();
        try {
            freeTerminals.add(terminal);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
