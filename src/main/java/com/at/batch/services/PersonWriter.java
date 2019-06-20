package com.at.batch.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.at.batch.model.Person;
import com.at.batch.repository.PersonRepository;

public class PersonWriter implements ItemWriter<Person> {
	
	@Autowired PersonRepository personRepository;
	
	private static final Logger log = LoggerFactory.getLogger(PersonWriter.class);
	
	@Override
	public void write(List<? extends Person> items) throws Exception {
		log.info("Received the information of {} persons", items.size());
	    for(Person person:items)
	    {
	    	log.info(String.format("Person id : %d",person.getId()));
	        log.info(String.format("Insert data: %s %s %s", person.getFirstName(), person.getLastname(), person.getSalary()));
	        personRepository.save(person);
	        
	    }	
	}

}
