package ru.geekbrains.Lesson4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.geekbrains.Lesson4.Repositiry.CartRepository;

@Controller
public class CartController {
    CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showAllProducts(Model uiModel) {
        uiModel.addAttribute("cart", cartRepository.getCart());
        System.out.println(cartRepository.getCart());
        return "cart";
    }

    @RequestMapping(value = "/cart/delete", method = RequestMethod.GET)
    public String deleteFromCart() {
        cartRepository.deleteFromCart();
        return "redirect:/cart";
    }

}
