package com.at.batch.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Person {
	@Id	
	private Long id;
	private String firstName;
	private String lastname;
	private Long salary;
	public Person() {
		
	}
	
	public Person(Long id, String firstName, String lastName, Long salary) {
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastName;
		this.salary = salary;
	}
}
