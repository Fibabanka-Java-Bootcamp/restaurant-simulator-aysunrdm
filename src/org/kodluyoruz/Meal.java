package org.kodluyoruz;
class Meal {

    private final int orderNum;
    volatile int cleanUp = 0;

    public Meal(int orderNum) {
        this.orderNum = orderNum;//ordernum: masa numaras�
    }

    public String toString() {
        return "Masa " + orderNum;
    }
}