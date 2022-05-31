package ru.geekbrains.Lesson4.services;

import ru.geekbrains.Lesson4.entities.Product;
import ru.geekbrains.Lesson4.entities.ProductDto;
import ru.geekbrains.Lesson4.entities.ProductMapper;
import ru.geekbrains.Lesson4.repositiry.ProductRepository;

import java.util.List;

public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto save(ProductDto productDto) {
        Product product = ProductMapper.MAPPER.toProduct(productDto);
        product = productRepository.save(product);
        return ProductMapper.MAPPER.fromProduct(product);
    }

    public List<ProductDto> findAll() {
        return ProductMapper.MAPPER.fromProductList(productRepository.findAll());
    }

    public ProductDto findOne(int id) {
        return ProductMapper.MAPPER.fromProduct(productRepository.findById(id).get());
    }

}
