package LoginSystem.com.controller;


import LoginSystem.com.model.Role;
import LoginSystem.com.model.User;
import LoginSystem.com.repository.UserRepository;
import LoginSystem.com.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage(Model model, Principal principal) {
//        if (principal != null) {
//            model.addAttribute("loggedIn", true);
//        } else {
//            model.addAttribute("loggedIn", false);
//        }
        return "index";
    }

    @GetMapping("/about")
    public String viewAbout(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("loggedIn", true);
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "about";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup_form";
        }

        if (service.emailExists(user.getEmail())) {
            model.addAttribute("errorMessage", "Email already exists!");
            return "signup_form";
        }

        service.saveWithDefaultRole(user);
        return "register_success";
    }


    @GetMapping("/users")
    public String viewListUsers(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = service.get(id);
        List<Role> listRoles = service.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String editCurrentUser(Model model, Principal principal) {
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
        model.addAttribute("user", currentUser);
        return "Profilepage";
    }

    @PostMapping("/edit-profile")
    public String saveCurrentUser(@ModelAttribute("user") User user, Principal principal) {
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
//        currentUser.setFirstName(user.getFirstName());
//        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        service.save(currentUser);
        return "Profilepage";
    }


}

