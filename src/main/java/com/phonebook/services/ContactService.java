package com.phonebook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phonebook.model.Contact;
import com.phonebook.repository.ContactRepository;

@Service
public class ContactService {
	
	private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository branchRepository){
        this.contactRepository = branchRepository;
    }

	public List<Contact> getAllContacts() {
		return this.contactRepository.findAll();
	}
	
	public List<Contact> getContactsByFilter(String filter) {
		if (filter != null && !filter.isEmpty()) {
			return this.contactRepository.findByFilter(filter.toUpperCase());
		} else {
			return this.contactRepository.findAll();
		}
	}
	
	public Contact getContact(Long id) {
		return this.contactRepository.findOne(id);
	}

	public Contact addContact(Contact contact) {
		this.contactRepository.saveAndFlush(contact);
		return contact;
	}

	public void deleteContact(Long id) {
		Contact contact = getContact(id);
		this.contactRepository.delete(contact);
	}

}
