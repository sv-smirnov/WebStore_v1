package ru.geekbrains.Lesson4.Services;

import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Entities.ProductDto;
import ru.geekbrains.Lesson4.Entities.ProductMapper;
import ru.geekbrains.Lesson4.Repositiry.ProductRepository;

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
