package ru.geekbrains.Lesson4.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers", schema = "product")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public Customer() {
    }
    public Customer(String name) {
        this.name = name;
    }
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Order> getProducts() {
        return orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
