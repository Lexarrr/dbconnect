package org.example;

public class Kayak {

        int id;
        int max_weight;

    public Kayak(int id, int max_weight) {
        this.id = id;
        this.max_weight = max_weight;

    }

    @Override
    public String toString() {
        return "Kayak{" +
                "id=" + id +
                ", max_weight=" + max_weight +
                '}';
    }
}
