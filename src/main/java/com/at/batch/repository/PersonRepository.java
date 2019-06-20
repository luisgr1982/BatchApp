package com.at.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.at.batch.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
