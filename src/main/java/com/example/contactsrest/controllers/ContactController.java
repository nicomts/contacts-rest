package com.example.contactsrest.controllers;

import com.example.contactsrest.models.ContactModel;
import com.example.contactsrest.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    ContactService contactService;

    @GetMapping()
    public ArrayList<ContactModel> getContacts(){
        return contactService.getContacts();
    }

    @PostMapping()
    public ContactModel saveContact(@RequestBody ContactModel contact){
        return this.contactService.saveContact(contact);
    }
}
