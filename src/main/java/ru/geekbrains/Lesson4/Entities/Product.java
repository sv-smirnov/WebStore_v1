package ru.geekbrains.Lesson4.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "names", schema = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private double cost;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Order> orders;

    public Product() {
    }

    public Product(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
    public Product(String name, double cost) {
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

    public List<Order> getOrders() {
        return orders;
    }

}
