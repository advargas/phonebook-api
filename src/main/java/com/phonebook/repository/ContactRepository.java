package com.phonebook.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phonebook.model.Contact;

@Transactional
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("SELECT c FROM Contact c where upper(c.name) like %:filter% or upper(c.surname) like %:filter%")
    List<Contact> findByFilter(@Param("filter") String filter);
}
