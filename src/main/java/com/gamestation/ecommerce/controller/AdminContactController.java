package com.gamestation.ecommerce.controller;

import com.gamestation.ecommerce.model.Contact;
import com.gamestation.ecommerce.model.Role;
import com.gamestation.ecommerce.model.User;
import com.gamestation.ecommerce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // method for deleting messages, and reloading page
    @DeleteMapping("/{id}")
    public RedirectView deleteMessages(@PathVariable("id") Long id) {
        contactService.deleteMessage(id);
        return new RedirectView("/admin/category");
    }


//    @GetMapping("/show/{id}")
//    public ModelAndView getMessagePage(@PathVariable("id") Long id) {
//        ModelAndView mav = new ModelAndView("admin/messageDisplay");
//        Contact contact = contactService.getMessageById(id);
//        mav.addObject("messages", contact);
//        mav.addObject("allMessages", contactService.getMessageById(id));
//        return mav;
//    }

    @GetMapping("/show/{id}")
    public ModelAndView showMessage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/messageDisplay");
        Contact contact= contactService.getMessageById(id);
        mav.addObject("messages", contact);
        return mav;
    }


}
