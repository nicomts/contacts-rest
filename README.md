# Contacts REST API
Simple backend app to save contacts.

## Table of Contents
* [General Info](#general-information)
* [Features](#features)
* [Usage](#usage)
  * [Saving a contact](#saving-a-contact)
  * [Getting contacts](#getting-contacts)
  * [Getting contacts by id](#getting-contacts-by-id)
  * [Updating contacts](#updating-contacts)
  * [Deleting contacts](#deleting-contacts)
* [Technologies Used](#technologies-used)


## General Information
This app is intended to allow CRUD operations through a REST API. The use case is saving contacts.

## Features
- Save name, email and phone number of a contact
- Delete and update saved contacts


## Usage
The main logic is on the ".../contacts" url. From a localhost environment with port 8080 will be `localhost:8080/contacts`

### Saving a contact
The app saves a contact when it receives a JSON body through a HTTP POST request at the .../contacts URL.
For example:

POST `localhost:8080/contacts`
```
{
    "name" : "John",
    "email" : "johndoe@gmail.com",
    "phoneNumber" : "+12345678"
}
```

will save John as a new contact, with an auto assigned id.

### Getting contacts
The app sends a JSON with all the contacts when it receives a GET request at the ".../contacts" url
Simply loading `localhost:8080/contacts` on the web browser should get all the contacts saved.

### Getting contacts by id
The app sends a specified contact when it receives a GET request from the ".../contacts/id" URL

For example:

http://localhost:8080/contacts/1

Should load the contact details from the first contact (The one with id = 1)

### Updating contacts
The app updates an existing contact when it receives a POST with a specified id.

For example:

If John's id is 1, then

POST `localhost:8080/contacts`
```
{
    "id" : 1
    "name" : "John Doe",
    "email" : "johndoe@gmail.com",
    "phoneNumber" : "+12345678"
}
```
will update John's name

### Deleting contacts
The app will delete a contact when it receives a DELETE request at the ".../contacts/id" URL

For example:

DELETE `localhost:8080/contacts/1`

will delete the first contact.

## Technologies Used
- Java - version 20
- Spring Boot - version 3.1.2
- PostgreSQL - version 15.3



