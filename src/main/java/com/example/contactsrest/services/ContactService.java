package com.example.contactsrest.services;

import com.example.contactsrest.models.ContactModel;
import com.example.contactsrest.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    //This will find all the contacts and return them as an array
    public ArrayList<ContactModel> getContacts(){
        return (ArrayList<ContactModel>) contactRepository.findAll();
    }

    public ContactModel saveContact(ContactModel contact){
        return contactRepository.save(contact);
    }
}
