package ru.geekbrains.Lesson4.Entities;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto productDto);
    ProductDto fromProduct(Product product);

    List<Product> toProductList(List<ProductDto> productDtos);
    List<ProductDto> fromProductList(List<Product> products);
}
