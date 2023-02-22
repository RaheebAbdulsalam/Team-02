package LoginSystem.com.controller;

import java.util.List;

import LoginSystem.com.model.Product;
import LoginSystem.com.model.User;
import LoginSystem.com.service.ProductService;
import LoginSystem.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @RequestMapping("/register")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup_form";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.save(user);
            return "redirect:/login";
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getReason());
            return "signup_form";
        }
    }


    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/account")
    public String accountPage(Model model){
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);
        return "account";
    }

    @RequestMapping("/adminPanel")
    public String adminPage(Model model){
        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);
        return "adminPanel";
    }




}
