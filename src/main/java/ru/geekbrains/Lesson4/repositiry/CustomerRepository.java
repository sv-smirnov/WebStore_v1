package ru.geekbrains.Lesson4.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.Lesson4.entities.Customer;


@org.springframework.stereotype.Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByName(String name);

}
