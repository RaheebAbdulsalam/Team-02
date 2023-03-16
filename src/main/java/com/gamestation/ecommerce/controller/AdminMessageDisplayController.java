package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Contact;
import com.gamestation.ecommerce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin/messageDisplay")
public class AdminMessageDisplayController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ModelAndView displayMessageDetails() {
        ModelAndView mav = new ModelAndView("admin/messageDisplay");
        mav.addObject("messages", contactService.getAllMessages());
        return mav;
    }


    @GetMapping("/list")
    public ModelAndView getMessageList() {
        ModelAndView mav = new ModelAndView("admin/messageDisplay");
        mav.addObject("messages", contactService.getAllMessages());
        return mav;
    }

}
