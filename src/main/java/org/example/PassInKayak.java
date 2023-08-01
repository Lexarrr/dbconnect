package org.example;

public class PassInKayak {

     int id_kayak;
    int id_pass1;




    public PassInKayak(int id_kayak, int id_pass1) {
        this.id_kayak = id_kayak;
        this.id_pass1 = id_pass1;

    }

    public int getId_kayak() {
        return id_kayak;
    }

//    public void setId_kayak(int id_kayak) {
//        this.id_kayak = id_kayak;
//    }

    public int getId_pass1() {
        return id_pass1;
    }

    public void setId_pass1(int id_pass1) {
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
