package ru.geekbrains.Lesson4.Entities;

import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "product")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

}
