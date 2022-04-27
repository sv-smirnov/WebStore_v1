package ru.geekbrains.Lesson4.Repositiry;

import ru.geekbrains.Lesson4.Entities.Product;

import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Repository
public class CartRepository {
    ArrayList<Product> cart;

    public CartRepository() {
        cart = new ArrayList<Product>();
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void deleteFromCart() {
        cart.clear();
    }
}

