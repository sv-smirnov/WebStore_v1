package ru.geekbrains.Lesson4.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.Lesson4.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
