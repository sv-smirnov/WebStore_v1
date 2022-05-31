package ru.geekbrains.Lesson4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.entities.Customer;
import ru.geekbrains.Lesson4.entities.Order;
import ru.geekbrains.Lesson4.repositiry.CartRepository;
import ru.geekbrains.Lesson4.repositiry.CustomerRepository;
import ru.geekbrains.Lesson4.repositiry.ProductRepository;
import ru.geekbrains.Lesson4.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class CustomersController {
    CustomerRepository customerRepository;
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CustomersController(ProductRepository productRepository, CartRepository cartRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String showAllCustomers(Model uiModel) {
        uiModel.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @RequestMapping(value = "/customers/showInfo", method = RequestMethod.GET)
    public String showCustomerInfo(Principal principal, Model uiModel) {
        Customer customer = userService.findByName(principal.getName());
        List<Order> orderList = customer.getOrders();
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        uiModel.addAttribute("orders", orderList);
        return "orders";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authorization(@RequestParam(required = false) String customer, Model uiModel, @RequestParam (required = false) String password) {
        if (customerRepository.findByName(customer) != null) {
            uiModel.addAttribute("message", new String("Пользователь с таким именем уже существует"));
            return "registration";}
         else {customerRepository.save(new Customer(customer, new BCryptPasswordEncoder().encode(password)));
        return "redirect:/login";}
    }

    @RequestMapping(value = "/customers/delete", method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam(required = false) Integer id, Model uiModel) {
        if (id != null) {
            customerRepository.delete(customerRepository.getById(id));
            uiModel.addAttribute("customers", customerRepository.findAll());
            return "customers";
        } else return "customers";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String login(Model uiModel) {
        return "registration";
    }

}
