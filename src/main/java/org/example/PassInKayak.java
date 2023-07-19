package org.example;

public class PassInKayak {

    int id_kayak;
    int id_pass1;


    public PassInKayak(int id_kayak, int id_pass1) {
        this.id_kayak = id_kayak;
        this.id_pass1 = id_pass1;

    }

    @Override
    public String toString() {
        return "PassInKayak{" +
                "id_kayak=" + id_kayak +
                ", id_pass1=" + id_pass1 +
                '}';
    }
}
