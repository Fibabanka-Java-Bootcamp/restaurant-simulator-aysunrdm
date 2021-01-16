package org.kodluyoruz;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class WaitPerson implements Runnable {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    while (restaurant.meal == null) {
                        condition.await(); //sef bekleniyor
                    }
                } finally {
                    lock.unlock();
                }
                System.out.println("Musteri siparisini verdi." + restaurant.meal);

                restaurant.chef.lock.lock();
                try {
                    restaurant.meal = null;
                    System.out.println("Siparis 2 numarali sefe  iletildi !");
                    restaurant.chef.condition.signalAll();  //Masa bir baska musteri icin hazir
                } finally {
                    restaurant.chef.lock.unlock();
                }

                try {
                    restaurant.boy.lock.lock();
                    System.out.println("Garson siparisi masaya goturuyor. ");
                    restaurant.boy.condition.signalAll();
                } finally {
                    restaurant.boy.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Musteri gidiyor!");
        }
    }
}
