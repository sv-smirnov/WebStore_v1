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
    CustomerRepository customerRepository;

    public ProductsController(ProductRepository productRepository, CartRepository cartRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showAllProducts(@RequestParam(required = false) Integer id,@RequestParam(required = false) Integer customer, Model uiModel) {
        if (customer!=null) {id = customer;}
        uiModel.addAttribute("customer_id", id);
        uiModel.addAttribute("customer_name", customerRepository.findById(id).get().getName());
        uiModel.addAttribute("products", productRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/products/new", method = RequestMethod.GET)
    public String addProduct(@RequestParam(required = false) Integer id,@RequestParam(required = false) Integer customer, Model uiModel) {
        if (customer!=null) {id = customer;}
        uiModel.addAttribute("customer_id", id);
        uiModel.addAttribute("customer_name", customerRepository.findById(id).get().getName());
        return "newProduct";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String getForm(Model uiModel, @RequestParam(required = false) Integer customer, @RequestParam String name, @RequestParam Double cost) {
        productRepository.save(new Product(name, cost));
        uiModel.addAttribute("customer_id", customer);
        uiModel.addAttribute("customer_name", customerRepository.findById(customer).get().getName());
        uiModel.addAttribute("products", productRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/products/toCart", method = RequestMethod.GET)
    public String toCart(Model uiModel, @RequestParam(required = false) List<Integer> id, @RequestParam(required = false) Integer customer, @RequestParam(required = false) Integer addProd) {
//        if (id != null) {
//            for (int i = 0; i < id.size(); i++) {
//                cartRepository.addToCart(productRepository.getById(id.get(i)));
//            }
//        }
        if (addProd != null) {cartRepository.addToCart(productRepository.getById(addProd));}
        uiModel.addAttribute("customer_id", customer);
        uiModel.addAttribute("customer_name", customerRepository.findById(customer).get().getName());
        uiModel.addAttribute("cart", cartRepository.getCart());
        System.out.println(cartRepository.getCart());
        return "cart";
    }
    @RequestMapping(value = "/products/delete", method = RequestMethod.GET)
    public String deleteProduts(Model uiModel, @RequestParam(required = false) Integer customer,@RequestParam(required = false) List<Integer> id, @RequestParam(required = false) Integer delProd) {
//        if (id != null) {
//            for (int i = 0; i < id.size(); i++) {
//                productRepository.deleteById(id.get(i));
//            }
//        }
        if (delProd != null) {productRepository.deleteById(delProd);}
        uiModel.addAttribute("customer_id", customer);
        uiModel.addAttribute("customer_name", customerRepository.findById(customer).get().getName());
        uiModel.addAttribute("products", productRepository.findAll());
        return "home";
    }

}

