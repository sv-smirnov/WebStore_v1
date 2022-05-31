package ru.geekbrains.Lesson4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.Lesson4.entities.Customer;
import ru.geekbrains.Lesson4.entities.Order;
import ru.geekbrains.Lesson4.repositiry.CartRepository;
import ru.geekbrains.Lesson4.repositiry.CustomerRepository;
import ru.geekbrains.Lesson4.repositiry.OrderRepository;
import ru.geekbrains.Lesson4.repositiry.ProductRepository;
import ru.geekbrains.Lesson4.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
    CartRepository cartRepository;
    CustomerRepository customerRepository;
    OrderRepository orderRepository;
    ProductRepository productRepository;
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CartController(ProductRepository productRepository, CartRepository cartRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showAllProducts(Principal principal, Model uiModel) {
        Customer customer = userService.findByName(principal.getName());
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        uiModel.addAttribute("cart", cartRepository.getCart());
        System.out.println(cartRepository.getCart());
        return "cart";
    }

    @RequestMapping(value = "/cart/delete", method = RequestMethod.GET)
    public String deleteFromCart(@RequestParam(required = false) Integer customer, Model uiModel) {
        cartRepository.deleteFromCart();
        return "redirect:/cart?customer=" + customer;
    }

    @RequestMapping(value = "/cart/createOrder", method = RequestMethod.GET)
    public String createOrder(Principal principal, Model uiModel) {
        Customer customer = userService.findByName(principal.getName());
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        if (cartRepository != null) {
            for (int i = 0; i < cartRepository.getCart().size(); i++) {
                orderRepository.save(new Order(customerRepository.getById(customer.getId()), cartRepository.getCart().get(i)));
            }
        }
        cartRepository.getCart().clear();
        uiModel.addAttribute("products", productRepository.findAll());
        System.out.println(cartRepository.getCart());
        return "home";
    }

    @RequestMapping(value = "/cart/deleteOrder", method = RequestMethod.GET)
    public String deleteOrder(Principal principal, Model uiModel, @RequestParam(required = false) List<Integer> id) {
        Customer customer = userService.findByName(principal.getName());
        uiModel.addAttribute("customer_id", customer.getId());
        uiModel.addAttribute("customer_name", customer.getName());
        if (id != null) {
            for (int i = 0; i < id.size(); i++) {
                orderRepository.deleteById(id.get(i));
            }
            List<Order> orderList = customer.getOrders();
            uiModel.addAttribute("orders", orderList);
        }
        return "orders";
    }

}
