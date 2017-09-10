package com.phonebook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.phonebook.model.Contact;
import com.phonebook.services.ContactService;

@RestController
@RequestMapping("/api")
public class ContactController {
	
	private final ContactService contactService;

    @Autowired
    public ContactController(ContactService branchService){
        this.contactService = branchService;
    }
	
    @CrossOrigin(origins = {"http://localhost:3000","http://localhost"})
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public List<Contact> getAllContacts() {
		return this.contactService.getAllContacts();
	}
	
    @CrossOrigin(origins = {"http://localhost:3000","http://localhost"})
	@RequestMapping(value = "/contacts/{filter}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public List<Contact> getContactsByFilter(@PathVariable String filter) {
    	return this.contactService.getContactsByFilter(filter);
	}
	
    @CrossOrigin(origins = {"http://localhost:3000","http://localhost"})
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Contact addContact(@Valid @RequestBody final Contact contact) {
		return this.contactService.addContact(contact);
	}

    @CrossOrigin(origins = {"http://localhost:3000","http://localhost"})
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public boolean deleteContact(@PathVariable Long id) {
		this.contactService.deleteContact(id);
		return true;
	}

}
