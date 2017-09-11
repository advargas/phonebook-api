package com.phonebook;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.phonebook.model.Contact;
import com.phonebook.services.ContactService;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class ContactIT {
	
	@Value("${local.server.port}")
    private int port;
	
	@Autowired
    private ContactService contactService;
	
	@Test
	public void testGetAllContacts() {
		
		List<Contact> contacts = new ArrayList<>();
		Mockito.when(contactService.getAllContacts()).thenReturn(contacts);
		
		RestAssured.
	        given().
	          port(port).
	          accept(MediaType.APPLICATION_JSON_VALUE).
	          contentType(MediaType.APPLICATION_JSON_VALUE).
	        when().
	          get("/api/contacts").
	        then().
	           statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testGetContactsByFilter() {
		
		List<Contact> contacts = new ArrayList<>();
		Mockito.when(contactService.getAllContacts()).thenReturn(contacts);
		
		RestAssured.
	        given().
	          pathParam("filter", "and").
	          port(port).
	          accept(MediaType.APPLICATION_JSON_VALUE).
	          contentType(MediaType.APPLICATION_JSON_VALUE).
	        when().
	          get("/api/contacts/{filter}").
	        then().
	           statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testAddContact() {
		
		Contact contact = new Contact();
		contact.setName("Andrea");
		contact.setSurname("Castro");
		contact.setPhone("45345435");
		
		Mockito.when(contactService.addContact(contact)).thenReturn(contact);
		
		RestAssured.
	        given().
	          body(contact).
	          port(port).
	          accept(MediaType.APPLICATION_JSON_VALUE).
	          contentType(MediaType.APPLICATION_JSON_VALUE).
	        when().
	          post("/api/contact").
	        then().
	           statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testAddContactError() {
		
		Contact contact = new Contact();
		contact.setName("Andrea");
		contact.setSurname("Castro");
		contact.setPhone(null);
		
		Mockito.when(contactService.addContact(contact)).thenReturn(contact);
		
		RestAssured.
        given().
          body(contact).
          port(port).
          accept(MediaType.APPLICATION_JSON_VALUE).
          contentType(MediaType.APPLICATION_JSON_VALUE).
        when().
          post("/api/contact").
        then().
           statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void testDeleteContact() {
		
		Contact contact = new Contact();
		contact.setName("Andrea");
		contact.setSurname("Castro");
		contact.setPhone(null);
		
		Mockito.when(contactService.getContact(5l)).thenReturn(contact);
		
		RestAssured.
        given().
          pathParam("id", "5").
          port(port).
          accept(MediaType.APPLICATION_JSON_VALUE).
          contentType(MediaType.APPLICATION_JSON_VALUE).
        when().
          delete("/api/contact/{id}").
        then().
           statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testDeleteContactInvalid() {
		
		Contact contact = new Contact();
		contact.setName("Andrea");
		contact.setSurname("Castro");
		contact.setPhone(null);
		
		Mockito.when(contactService.getContact(5l)).thenReturn(null);
		
		RestAssured.
        given().
          pathParam("id", "5").
          port(port).
          accept(MediaType.APPLICATION_JSON_VALUE).
          contentType(MediaType.APPLICATION_JSON_VALUE).
        when().
          delete("/api/contact/{id}").
        then().
           statusCode(HttpStatus.OK.value());
	}
}
