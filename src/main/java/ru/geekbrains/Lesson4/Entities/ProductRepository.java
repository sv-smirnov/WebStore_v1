package ru.geekbrains.Lesson4.Entities;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private ArrayList<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product(1, "product1", 100));
        products.add(new Product(2, "product2", 200));
        products.add(new Product(3, "product3", 300));
        products.add(new Product(4, "product4", 400));
        products.add(new Product(5, "product5", 500));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.get(id);
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}

