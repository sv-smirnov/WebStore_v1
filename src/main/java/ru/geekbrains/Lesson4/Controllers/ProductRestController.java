package ru.geekbrains.Lesson4.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Entities.ProductDto;
import ru.geekbrains.Lesson4.Entities.ProductMapper;
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
    public List<ProductDto> getAllProducts() {
        return ProductMapper.MAPPER.fromProductList(productRepository.findAll());}

//    @GetMapping("/{id}")
//    public ProductDto getProductById (@PathVariable int id) {
//        return ProductMapper.MAPPER.fromProduct(productRepository.findById(id));}

    @PostMapping
    public Product addProduct (@RequestBody ProductDto productDto) {return productRepository.save(ProductMapper.MAPPER.toProduct(productDto));}

    @DeleteMapping("/{id}")
    public int deleteProduct (@PathVariable int id) {productRepository.deleteById(id); return HttpStatus.OK.value();}
}
