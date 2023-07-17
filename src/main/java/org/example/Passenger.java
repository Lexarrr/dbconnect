package org.example;

public class Passenger {

    int id;
    String name;
    int weight;

    public Passenger(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
