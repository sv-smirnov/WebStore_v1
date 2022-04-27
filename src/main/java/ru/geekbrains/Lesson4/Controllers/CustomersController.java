package ru.geekbrains.Lesson4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Repositiry.CartRepository;
import ru.geekbrains.Lesson4.Repositiry.CustomerRepository;
import ru.geekbrains.Lesson4.Repositiry.ProductRepository;

@Controller
public class CustomersController {
    CustomerRepository customerRepository;

    public CustomersController(ProductRepository productRepository, CartRepository cartRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String showAllCustomers(Model uiModel) {
        uiModel.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @RequestMapping(value = "/customers/showInfo", method = RequestMethod.GET)
    public String showCustomerInfo(@RequestParam(required = false) Integer id, Model uiModel) {
        uiModel.addAttribute("products", customerRepository.getById(id).getProducts());
        return "home";
    }

}
