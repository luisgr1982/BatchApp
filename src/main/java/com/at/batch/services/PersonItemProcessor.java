package com.at.batch.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.at.batch.model.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
	
	@Override
	public Person process(final Person person) throws Exception {
		// metodo que cambia los datos a UpperCase
		final Long id = person.getId();
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastname().toUpperCase();
		final Long salary = person.getSalary();		
		
		final Person transformPerson = new Person(id, firstName, lastName, salary);

		log.info("Convert (" + person + ") to (" + transformPerson + ")");

		return transformPerson;
	}

}
