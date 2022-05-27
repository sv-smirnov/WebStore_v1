package ru.geekbrains.Lesson4.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    private double cost;
}
