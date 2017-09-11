package com.phonebook;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import com.phonebook.model.Contact;
import com.phonebook.repository.ContactRepository;
import com.phonebook.services.ContactService;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
	
	@InjectMocks
    private ContactService contactService;
	
	@Mock
	private ContactRepository contactRepository;
	
	@Test
	public void testGetAllContacts() {
		
		Contact contact = new Contact();
		contact.setName("Andrea");
		contact.setSurname("Castro");
		contact.setPhone("45345435");
		
		List<Contact> contacts = new ArrayList<>();
		contacts.add(contact);
		Mockito.when(contactRepository.findAll()).thenReturn(contacts);
		
		List<Contact> list = contactService.getAllContacts();
		Assert.notEmpty(list, "Not empty contacts");
	}
	
	@Test
	public void testGetContactsByFilter() {
		
		Contact contact = new Contact();
		contact.setName("Andrea");
		contact.setSurname("Castro");
		contact.setPhone("45345435");
		
		List<Contact> contacts = new ArrayList<>();
		contacts.add(contact);
		Mockito.when(contactRepository.findByFilter("and")).thenReturn(contacts);
		
		List<Contact> list = contactService.getContactsByFilter("and");
		Assert.notEmpty(list, "Not empty contacts");
	}
	
	@Test
	public void testAddContact() {
		
		Contact contact = new Contact();
		contact.setName("Andrea");
		contact.setSurname("Castro");
		contact.setPhone("45345435");
		
		Mockito.when(contactRepository.saveAndFlush(contact)).thenReturn(contact);
		
		Contact newContact = contactService.addContact(contact);
		Assert.notNull(newContact, "Not null contact");
		
	}

}
