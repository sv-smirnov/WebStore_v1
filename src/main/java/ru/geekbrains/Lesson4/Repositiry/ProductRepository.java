package ru.geekbrains.Lesson4.Repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.Lesson4.Entities.Product;

@org.springframework.stereotype.Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

