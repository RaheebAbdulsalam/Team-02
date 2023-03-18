package com.gamestation.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminHomeController {

    // Returns admin home page
    @GetMapping
    public ModelAndView getAdminHomePage() {
        ModelAndView mav = new ModelAndView("admin/index");
        return mav;
    }


}