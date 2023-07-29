package com.example.contactsrest.controllers;

import com.example.contactsrest.models.ContactModel;
import com.example.contactsrest.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping(path = "/{id}")
    public Optional<ContactModel> getContactById(@PathVariable("id") Long id) {
        return this.contactService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.contactService.deleteContact(id);
        if (ok){
            return "Contact with id " + id + " deleted";
        }else{
            return "Contact with id " + id + " could not be deleted";
        }
    }
}
