package ru.geekbrains.Lesson4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Lesson4.Entities.Customer;
import ru.geekbrains.Lesson4.Entities.Order;
import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Repositiry.CartRepository;
import ru.geekbrains.Lesson4.Repositiry.CustomerRepository;
import ru.geekbrains.Lesson4.Repositiry.ProductRepository;

import java.util.ArrayList;
import java.util.List;

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
        List<Order> orderList=  customerRepository.getById(id).getOrders();
        List<Product> productList = new ArrayList<Product>();
        for (int k = 0; k < orderList.size(); k++) {
            productList.add(orderList.get(k).getProduct());
        }
        uiModel.addAttribute("products", productList);
        return "orders";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authorization(@RequestParam(required = false) String name, Model uiModel) {
        if (customerRepository.findByName(name) !=null) {
        List<Order> orderList = customerRepository.findByName(name).getOrders();
        List<Product> productList = new ArrayList<Product>();
        for (int k = 0; k < orderList.size(); k++) {
            productList.add(orderList.get(k).getProduct());
        }
        uiModel.addAttribute("products", productList);}
        else customerRepository.save(new Customer(name));
        return "orders";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model uiModel) {
        return "registration";
    }

}
