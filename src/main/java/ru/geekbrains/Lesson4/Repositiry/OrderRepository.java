package ru.geekbrains.Lesson4.Repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.Lesson4.Entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
