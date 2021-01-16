package org.kodluyoruz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {

    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    Chef chef2=new Chef(this);
    BusBoy boy = new BusBoy(this);
    BusBoy boy2 = new BusBoy(this);
    BusBoy boy3 = new BusBoy(this);

    public Restaurant() {
        exec.execute(chef);//sef
        exec.execute(waitPerson);//musteri
        exec.execute(boy);//garson
    }

    public static void main(String[] args) {
        new Restaurant();
    }

}
