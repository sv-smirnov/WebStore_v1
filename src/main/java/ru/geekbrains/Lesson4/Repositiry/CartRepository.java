package ru.geekbrains.Lesson4.Repositiry;

import org.aspectj.apache.bcel.Repository;
import ru.geekbrains.Lesson4.Entities.Product;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class CartRepository extends Repository {
    ArrayList<Product> cart = new ArrayList<Product>();

    public CartRepository() {
        this.cart = cart;
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

