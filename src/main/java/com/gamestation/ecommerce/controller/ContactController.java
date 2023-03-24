package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Contact;
import com.gamestation.ecommerce.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
    public ModelAndView viewContactPage() {
        ModelAndView modelAndView = new ModelAndView("contact");
        modelAndView.addObject("contact", new Contact());
        return modelAndView;
    }

    @PostMapping("/submit-form")
    public ModelAndView submitForm(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return new ModelAndView("contact");
        //return new ModelAndView("success"); // success page
    }

    @GetMapping("/successMessage")
    public ModelAndView viewSuccessMessage(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("successMessage");
        return mav;
    }
}
