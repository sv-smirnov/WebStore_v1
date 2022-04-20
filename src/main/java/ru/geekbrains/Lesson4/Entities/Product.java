package ru.geekbrains.Lesson4.Entities;

import javax.persistence.*;

@Entity
@Table (name = "names")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String name;
    @Column
    private double cost;

    public Product(){}

    public Product(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
