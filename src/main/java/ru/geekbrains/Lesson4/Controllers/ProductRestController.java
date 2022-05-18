package ru.geekbrains.Lesson4.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Repositiry.ProductRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/rest/products")
@RestController
public class ProductRestController {
    ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping
    public List<Product> getAllProducts() {return productRepository.findAll();}

    @GetMapping("/{id}")
    public Optional<Product> getProductById (@PathVariable int id) {return productRepository.findById(id);}

    @PostMapping
    public Product addProduct (@RequestBody Product product) {return productRepository.save(product);}

    @DeleteMapping("/{id}")
    public int deleteProduct (@PathVariable int id) {productRepository.deleteById(id); return HttpStatus.OK.value();}
}
