package ru.geekbrains.Lesson4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Repositiry.CartRepository;
import ru.geekbrains.Lesson4.Repositiry.CustomerRepository;
import ru.geekbrains.Lesson4.Repositiry.ProductRepository;

import java.util.List;

@Controller
public class ProductsController {
    ProductRepository productRepository;
    CartRepository cartRepository;

    public ProductsController(ProductRepository productRepository, CartRepository cartRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showAllProducts(Model uiModel) {
        uiModel.addAttribute("products", productRepository.findAll());
        return "home";
    }

    @GetMapping("/products/new")
    public String newProduct() {
        return "newProduct";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String getForm(@RequestParam Integer id, @RequestParam String name, @RequestParam Double cost) {
        productRepository.save(new Product(id, name, cost));
        return "redirect:/products";
    }

    @RequestMapping(value = "/products/toCart", method = RequestMethod.GET)
    public String toCart(@RequestParam(required = false) List<Integer> id) {
        if (id != null) {
            for (int i = 0; i < id.size(); i++) {
                cartRepository.addToCart(productRepository.getById(id.get(i)));
            }
        }
        System.out.println(cartRepository.getCart());
        return "redirect:/products";
    }

}

