//package com.gamestation.ecommerce.controller;
//
//
//import com.gamestation.ecommerce.model.Contact;
//import com.gamestation.ecommerce.model.Product;
//import com.gamestation.ecommerce.service.ContactService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
//
//
//@RestController
//@RequestMapping("/admin/messageDisplay")
//public class AdminMessageDisplayController {
//
//    @Autowired
//    private ContactService contactService;
//
//    @GetMapping
//    public ModelAndView displayMessageDetails() {
//        ModelAndView mav = new ModelAndView("admin/messageDisplay");
//        mav.addObject("messages", contactService.getAllMessages());
//        return mav;
//    }
////
////
////    @GetMapping("/show")
////    public ModelAndView getMessageList() {
////        ModelAndView mav = new ModelAndView("admin/messageDisplay");
////        mav.addObject("messages", contactService.getAllMessages());
////        return mav;
////    }
//
//    @GetMapping("/show/{id}")
//    public ModelAndView getMessagePage(@PathVariable("id") Long id) {
//        ModelAndView mav = new ModelAndView("admin/messageDisplay");
//        Contact contact = contactService.getMessageById(id);
//        mav.addObject("messages", contact);
//        mav.addObject("allMessages", contactService.getMessageById(id));
//        return mav;
//    }
//
//
//}
