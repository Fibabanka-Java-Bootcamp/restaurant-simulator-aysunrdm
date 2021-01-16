package org.kodluyoruz;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Chef implements Runnable {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private Restaurant restaurant;
    private int count = 0;
    private int chefId;

    public Chef(Restaurant r) {
        restaurant = r;
        this.chefId=chefId;
    }

    public void run() {
    	
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {

                    while (restaurant.meal != null) {
                        condition.await(); //Siparis sefe iletiliyor
                        
                    }
                } finally {
                    lock.unlock();
                }

                if (++count == 5) {
                    System.out.println("Bos masa kalmadı!");
                    restaurant.exec.shutdownNow();
                    return;
                }
                System.out.println("Musteri masaya geldi!");
                restaurant.waitPerson.lock.lock();
                try {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Sef isini bitirdi!");
        }
    }
}
