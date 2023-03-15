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

    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    public Contact getMessageById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
    }
}
