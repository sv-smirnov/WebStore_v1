package ru.geekbrains.Lesson4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.entities.Customer;
import ru.geekbrains.Lesson4.entities.Product;
import ru.geekbrains.Lesson4.repositiry.CartRepository;
import ru.geekbrains.Lesson4.repositiry.CustomerRepository;
import ru.geekbrains.Lesson4.repositiry.ProductRepository;
import ru.geekbrains.Lesson4.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductsController {
    ProductRepository productRepository;
    CartRepository cartRepository;
    CustomerRepository customerRepository;
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ProductsController(ProductRepository productRepository, CartRepository cartRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }
    @RequestMapping("/")
    public String home(){
        return "registration";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showAllProducts(Principal principal, Model uiModel) {
        Customer customer = userService.findByName(principal.getName());
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        uiModel.addAttribute("products", productRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/products/new", method = RequestMethod.GET)
    public String addProduct(Principal principal, Model uiModel) {
        Customer customer = userService.findByName(principal.getName());
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        return "newProduct";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String getForm(Principal principal, Model uiModel, @RequestParam String name, @RequestParam Double cost) {
        Customer customer = userService.findByName(principal.getName());
        productRepository.save(new Product(name, cost));
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        uiModel.addAttribute("products", productRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/products/toCart", method = RequestMethod.GET)
    public String toCart(Model uiModel, Principal principal,  @RequestParam(required = false) List<Integer> id, @RequestParam(required = false) Integer addProd) {
        Customer customer = userService.findByName(principal.getName());
        if (addProd != null) {cartRepository.addToCart(productRepository.getById(addProd));}
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        uiModel.addAttribute("cart", cartRepository.getCart());
        System.out.println(cartRepository.getCart());
        return "cart";
    }
    @RequestMapping(value = "/products/delete", method = RequestMethod.GET)
    public String deleteProduts(Model uiModel,Principal principal, @RequestParam(required = false) List<Integer> id, @RequestParam(required = false) Integer delProd) {
        Customer customer = userService.findByName(principal.getName());
        if (delProd != null) {productRepository.deleteById(delProd);}
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        uiModel.addAttribute("products", productRepository.findAll());
        return "home";
    }

}

