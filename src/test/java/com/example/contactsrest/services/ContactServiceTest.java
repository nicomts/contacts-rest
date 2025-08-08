package com.example.contactsrest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.contactsrest.models.ContactModel;
import com.example.contactsrest.repositories.ContactRepository;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    private ContactModel testContact;

    @BeforeEach // Marks a method to be run before each test method in the class
    void setUp() {
        testContact = new ContactModel();
        testContact.setId(1L);
        testContact.setName("John Doe");
        testContact.setPhoneNumber("+541234567890");
        testContact.setEmail("john.doe@example.com");
    }

    @Test
    void testGetContacts_ReturnsListOfContacts() {
        // Arrange
        ContactModel contact2 = new ContactModel();
        contact2.setId(2L);
        contact2.setName("Jane Smith");
        contact2.setPhoneNumber("+540987654321");
        contact2.setEmail("jane.smith@example.com");

        ArrayList<ContactModel> expectedContacts = new ArrayList<>(Arrays.asList(testContact, contact2));
        when(contactRepository.findAll()).thenReturn(expectedContacts);

        // Act
        ArrayList<ContactModel> actualContacts = contactService.getContacts();

        // Assert
        assertNotNull(actualContacts);
        assertEquals(2, actualContacts.size());
        assertEquals(expectedContacts, actualContacts);
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    void testGetContacts_ReturnsEmptyList() {
        // Arrange
        ArrayList<ContactModel> emptyList = new ArrayList<>();
        when(contactRepository.findAll()).thenReturn(emptyList);

        // Act
        ArrayList<ContactModel> actualContacts = contactService.getContacts();

        // Assert
        assertNotNull(actualContacts);
        assertTrue(actualContacts.isEmpty());
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    void testSaveContact_ReturnsContact() {
        // Arrange
        when(contactRepository.save(any(ContactModel.class))).thenReturn(testContact);

        // Act
        ContactModel savedContact = contactService.saveContact(testContact);

        // Assert
        assertNotNull(savedContact);
        assertEquals(testContact.getId(), savedContact.getId());
        assertEquals(testContact.getName(), savedContact.getName());
        assertEquals(testContact.getPhoneNumber(), savedContact.getPhoneNumber());
        assertEquals(testContact.getEmail(), savedContact.getEmail());
        verify(contactRepository, times(1)).save(testContact);
    }

    @Test
    void testSaveContact_WithNewContact() {
        // Arrange
        ContactModel newContact = new ContactModel();
        newContact.setName("New Contact");
        newContact.setPhoneNumber("+541234567890");
        newContact.setEmail("new@example.com");
        
        ContactModel savedContact = new ContactModel();
        savedContact.setId(3L);
        savedContact.setName("New Contact");
        savedContact.setPhoneNumber("+541234567890");
        savedContact.setEmail("new@example.com");
        
        when(contactRepository.save(newContact)).thenReturn(savedContact);

        // Act
        ContactModel result = contactService.saveContact(newContact);

        // Assert
        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("New Contact", result.getName());
        verify(contactRepository, times(1)).save(newContact);
    }

    @Test
    void testGetById_ExistingContact_ReturnsOptionalWithContact() {
        // Arrange
        when(contactRepository.findById(1L)).thenReturn(Optional.of(testContact));

        // Act
        Optional<ContactModel> result = contactService.getById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(testContact, result.get());
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NonExistingContact_ReturnsEmptyOptional() {
        // Arrange
        when(contactRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<ContactModel> result = contactService.getById(999L);

        // Assert
        assertFalse(result.isPresent());
        verify(contactRepository, times(1)).findById(999L);
    }

    @Test
    void testDeleteContact_SuccessfulDeletion_ReturnsTrue() {
        // Arrange
        doNothing().when(contactRepository).deleteById(1L);

        // Act
        boolean result = contactService.deleteContact(1L);

        // Assert
        assertTrue(result);
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteContact_ExceptionThrown_ReturnsFalse() {
        // Arrange
        doThrow(new RuntimeException("Database error")).when(contactRepository).deleteById(anyLong());

        // Act
        boolean result = contactService.deleteContact(1L);

        // Assert
        assertFalse(result);
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteContact_WithInvalidId_ReturnsFalse() {
        // Arrange
        Long invalidId = -1L;
        doThrow(new IllegalArgumentException("Invalid ID")).when(contactRepository).deleteById(invalidId);

        // Act
        boolean result = contactService.deleteContact(invalidId);

        // Assert
        assertFalse(result);
        verify(contactRepository, times(1)).deleteById(invalidId);
    }
}
