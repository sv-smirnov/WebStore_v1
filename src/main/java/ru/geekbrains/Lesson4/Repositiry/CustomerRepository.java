package ru.geekbrains.Lesson4.Repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.Lesson4.Entities.Customer;

import java.util.List;


@org.springframework.stereotype.Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByName(String name);

}
