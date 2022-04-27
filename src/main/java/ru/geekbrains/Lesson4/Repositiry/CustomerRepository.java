package ru.geekbrains.Lesson4.Repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.Lesson4.Entities.Customer;

@org.springframework.stereotype.Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}


