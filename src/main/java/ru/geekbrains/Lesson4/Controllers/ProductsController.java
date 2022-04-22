package ru.geekbrains.Lesson4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Repositiry.CartRepository;
import ru.geekbrains.Lesson4.Repositiry.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    ProductRepository productRepository;
    CartRepository cartRepository;

    public ProductsController(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
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

    @RequestMapping(value = "/toCart", method = RequestMethod.GET)
    public String toCart(@RequestParam List<Integer> id) {
        for (int i = 0; i < id.size(); i++) {
            cartRepository.addToCart(productRepository.getProductById(id.get(i)));
        }
        return "redirect:/products";
    }


}

