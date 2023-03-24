package com.gamestation.ecommerce.service;


import com.gamestation.ecommerce.exception.ResourceNotFoundException;
import com.gamestation.ecommerce.model.Contact;
import com.gamestation.ecommerce.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    //Get All messages
    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    //get message by using it's ID
    public Contact getMessageById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
    }

    //delete a message
    public void deleteMessage(Long id) {
        contactRepository.deleteById(id);
    }
}
