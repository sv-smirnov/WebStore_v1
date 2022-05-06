package ru.geekbrains.Lesson4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.Lesson4.Entities.Order;
import ru.geekbrains.Lesson4.Entities.Product;
import ru.geekbrains.Lesson4.Repositiry.CartRepository;
import ru.geekbrains.Lesson4.Repositiry.CustomerRepository;
import ru.geekbrains.Lesson4.Repositiry.OrderRepository;
import ru.geekbrains.Lesson4.Repositiry.ProductRepository;

import java.util.List;

@Controller
public class CartController {
    CartRepository cartRepository;
    CustomerRepository customerRepository;
    OrderRepository orderRepository;
    ProductRepository productRepository;

    public CartController(ProductRepository productRepository, CartRepository cartRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showAllProducts(@RequestParam(required = false) Integer customer, Model uiModel) {
        uiModel.addAttribute("customer_id", customer);
        uiModel.addAttribute("customer_name", customerRepository.findById(customer).get().getName());
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
    public String createOrder(@RequestParam(required = false) Integer customer, Model uiModel) {
        uiModel.addAttribute("customer_id", customer);
        uiModel.addAttribute("customer_name", customerRepository.findById(customer).get().getName());
        if (cartRepository != null) {
            for (int i = 0; i < cartRepository.getCart().size(); i++) {
                orderRepository.save(new Order(customerRepository.getById(customer), cartRepository.getCart().get(i)));
            }
        }
        cartRepository.getCart().clear();
        uiModel.addAttribute("products", productRepository.findAll());
        System.out.println(cartRepository.getCart());
        return "home";
    }

    @RequestMapping(value = "/cart/deleteOrder", method = RequestMethod.GET)
    public String deleteOrder(@RequestParam(required = false) Integer customer, Model uiModel, @RequestParam(required = false) List<Integer> id) {
        uiModel.addAttribute("customer_id", customer);
        uiModel.addAttribute("customer_name", customerRepository.findById(customer).get().getName());
        if (id != null) {
            for (int i = 0; i < id.size(); i++) {
                orderRepository.deleteById(id.get(i));
            }
            List<Order> orderList = customerRepository.findById(customer).get().getOrders();
            uiModel.addAttribute("orders", orderList);
        }
        return "orders";
    }

}
