package com.example.contactsrest.repositories;

import com.example.contactsrest.models.ContactModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
// Extend from CrudRepository because it has all the CRUD methods
// It needs the type of the entity (ContactModel) and the type of the id (Long)
public interface ContactRepository extends CrudRepository<ContactModel, Long> {
}
