package ru.geekbrains.Lesson4.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.Lesson4.entities.Product;

@org.springframework.stereotype.Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

