package org.example;

public class PassInKayak {

    int id_kayak;
    int id_pass1;
    int id_pass2;

    public PassInKayak(int id_kayak, int id_pass1, int id_pass2) {
        this.id_kayak = id_kayak;
        this.id_pass1 = id_pass1;
        this.id_pass2 = id_pass2;
    }

    @Override
    public String toString() {
        return "PassInKayak{" +
                "id_kayak=" + id_kayak +
                ", id_pass1=" + id_pass1 +
                ", id_pass2=" + id_pass2 +
                '}';
    }
}
