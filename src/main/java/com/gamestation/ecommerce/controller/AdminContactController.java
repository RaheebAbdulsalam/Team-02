package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Contact;
import com.gamestation.ecommerce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/admin/messages")
public class AdminContactController {

    @Autowired
    private ContactService contactService;

    // Returns admin messages page and messages list
    @GetMapping
    public ModelAndView getAdminMessagesPage() {
        ModelAndView mav = new ModelAndView("admin/messages");
        mav.addObject("messages", contactService.getAllMessages());
        return mav;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Contact>> getAllCategories() {
        List<Contact> messages = contactService.getAllMessages();
        if(messages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getMesssageById(@PathVariable("id") Long id) {
       Contact message = contactService.getMessageById(id);
        if(message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // method for deleting messages, and reloading page
    @DeleteMapping("/{id}")
    public RedirectView deleteMessages(@PathVariable("id") Long id) {
        contactService.deleteMessage(id);
        return new RedirectView("/admin/category");
    }
}
