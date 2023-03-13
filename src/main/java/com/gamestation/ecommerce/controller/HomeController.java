package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Category;
import com.gamestation.ecommerce.model.User;
import com.gamestation.ecommerce.repository.UserRepository;
import com.gamestation.ecommerce.service.CategoryService;
import com.gamestation.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HomeController {


    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CategoryService categoryService;

    /* Code to return pages */
    // Returns home page
    @GetMapping()
    public ModelAndView viewHomePage(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    // Returns about us page
    @GetMapping("/about")
    public ModelAndView viewAbout(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("about");
        return mav;
    }



    // returns register pages
    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView mav = new ModelAndView("signup_form");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/process_register")
    public ModelAndView processRegister(@Valid User user, BindingResult result) {
        ModelAndView mav;
        if (result.hasErrors()) {
            mav = new ModelAndView("signup_form");
            return mav;
        }

        if (service.emailExists(user.getEmail())) {
            mav = new ModelAndView("signup_form");
            mav.addObject("errorMessage", "Email already exists!");
            return mav;
        }

        service.saveWithDefaultRole(user);
        mav = new ModelAndView("register_success");
        return mav;
    }

    // returns login and profile pages

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView editCurrentUser(Principal principal) {
        ModelAndView mav = new ModelAndView("Profilepage");
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
        mav.addObject("user", currentUser);
        return mav;
    }

    @PostMapping("/edit-profile")
    public ModelAndView saveCurrentUser(@ModelAttribute("user") User user, Principal principal) {
        ModelAndView mav = new ModelAndView("Profilepage");
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        service.save(currentUser);
        return mav;
    }


    /* Code to load categories and products onto homepage */
    // 1-5 for now
    @GetMapping("/categoryproducts/{id}")
    public ModelAndView getCategoryPage(@PathVariable("id") Integer id) {
        Category category = categoryService.getCategoryById(id);
        ModelAndView mav = new ModelAndView("productPage");
        mav.addObject("category", category);
        mav.addObject("products", category.getProducts());
        return mav;
    }


}
