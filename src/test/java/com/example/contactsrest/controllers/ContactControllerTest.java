package com.example.contactsrest.controllers;

import com.example.contactsrest.models.ContactModel;
import com.example.contactsrest.services.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactControllerTest {
    @Mock
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetContacts() {
        ArrayList<ContactModel> contacts = new ArrayList<>();
        contacts.add(new ContactModel());
        when(contactService.getContacts()).thenReturn(contacts);

        ArrayList<ContactModel> result = contactController.getContacts();
        assertEquals(1, result.size());
        verify(contactService, times(1)).getContacts();
    }

    @Test
    void testSaveContact() {
        ContactModel contact = new ContactModel();
        when(contactService.saveContact(contact)).thenReturn(contact);

        ContactModel result = contactController.saveContact(contact);
        assertEquals(contact, result);
        verify(contactService, times(1)).saveContact(contact);
    }

    @Test
    void testGetContactById() {
        ContactModel contact = new ContactModel();
        when(contactService.getById(1L)).thenReturn(Optional.of(contact));

        Optional<ContactModel> result = contactController.getContactById(1L);
        assertTrue(result.isPresent());
        assertEquals(contact, result.get());
        verify(contactService, times(1)).getById(1L);
    }

    @Test
    void testDeleteByIdSuccess() {
        when(contactService.deleteContact(1L)).thenReturn(true);
        String result = contactController.deleteById(1L);
        assertEquals("Contact with id 1 deleted", result);
        verify(contactService, times(1)).deleteContact(1L);
    }

    @Test
    void testDeleteByIdFailure() {
        when(contactService.deleteContact(2L)).thenReturn(false);
        String result = contactController.deleteById(2L);
        assertEquals("Contact with id 2 could not be deleted", result);
        verify(contactService, times(1)).deleteContact(2L);
    }
}
