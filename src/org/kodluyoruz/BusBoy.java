package org.kodluyoruz;
import java.util.concurrent.locks.*;

class BusBoy implements Runnable {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    Restaurant restaurant;

    BusBoy(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {

                lock.lock();
                try {
                    condition.await();
                    System.out.println("Musteri yemegini tamamladi! ");
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Garson siparisi aldi.");
        }
    }

}