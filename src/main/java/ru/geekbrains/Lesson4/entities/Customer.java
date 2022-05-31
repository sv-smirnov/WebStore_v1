package ru.geekbrains.Lesson4.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers", schema = "product")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        this.role = "ROLE_USER";
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

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
