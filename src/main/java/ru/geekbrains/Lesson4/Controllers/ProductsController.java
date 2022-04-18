package ru.geekbrains.Lesson4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Entities.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductsController {
    ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String showAllProducts(Model uiModel) {
        uiModel.addAttribute("products", productRepository.getProducts());
        return "home";
    }

    @GetMapping("/new")
    public String newProduct() {
        return "newProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getForm(@RequestParam Integer id, @RequestParam String name, @RequestParam Double cost) {
        productRepository.addProduct(new Product(id, name, cost));
        return "redirect:/products";
    }
}

