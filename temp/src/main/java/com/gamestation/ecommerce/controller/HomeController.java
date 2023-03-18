package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.*;
import com.gamestation.ecommerce.repository.OrderRepository;
import com.gamestation.ecommerce.repository.UserRepository;
import com.gamestation.ecommerce.service.CategoryService;
import com.gamestation.ecommerce.service.OrderService;
import com.gamestation.ecommerce.service.ProductService;
import com.gamestation.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    /* Code to return pages */
    // Returns home page
    @GetMapping()
    public ModelAndView viewHomePage(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("index");
        if (principal != null) {
            mav.addObject("loggedIn", true);
        } else {
            mav.addObject("loggedIn", false);
        }
        return mav;
    }

    // Returns about us page
    @GetMapping("/about")
    public ModelAndView viewAbout(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("about");
        if (principal != null) {
            mav.addObject("loggedIn", true);
        } else {
            mav.addObject("loggedIn", false);
        }
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

    // returns user order page
    @GetMapping("/profile/orders")
    public ModelAndView showOrders(Principal principal) {
        ModelAndView mav = new ModelAndView("userOrders");
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
        List<Order> orders = orderRepo.findByUserId(currentUser.getId());
        mav.addObject("userOrders", orders);
        return mav;
    }

    // cancels orders
    @PutMapping("/profile/orders/{order_id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable ("order_id") Integer orderId, @RequestParam String status, HttpServletResponse response) throws IOException {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setStatus(status);
        orderService.updateOrder(orderId, order);
        response.sendRedirect("/profile/orders");
        return null;
    }

    // view further order details
    @GetMapping("/profile/orders/orderdetail/{orderId}")
    public ModelAndView showOrderPage(@PathVariable Integer orderId) {
        ModelAndView mav = new ModelAndView("userOrderDetails");
        Order order = orderService.getOrderById(orderId);
        mav.addObject("order", order);
        return mav;
    }

    // cancel individual items
    @PutMapping("/profile/orders/orderdetail/{orderId}/{itemId}")
    public ResponseEntity<?> cancelItem(@PathVariable Integer orderId, @PathVariable Integer itemId, @RequestParam String status, HttpServletResponse response) throws IOException {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        OrderItem orderItem = null;
        for (OrderItem item: order.getOrderItems()) {
            if (item.getId() == itemId){
                orderItem = item;
            }
        }

        if (orderItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderItem.setStatus(status);
        orderService.updateOrderItem(orderId, order, itemId); // pass in the itemId to update the specific OrderItem
        response.sendRedirect("/profile/orders/orderdetail/" + orderId);
        return null;
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

    @GetMapping("/product/{id}")
    public ModelAndView getProductPage(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        ModelAndView mav = new ModelAndView("productDetail");
        mav.addObject("product", product);
        return mav;
    }


}
