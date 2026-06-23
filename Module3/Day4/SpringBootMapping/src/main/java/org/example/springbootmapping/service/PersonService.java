package org.example.springbootmapping.service;

import org.example.springbootmapping.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
	Person addPerson(Person person);

	List<Person> getAll();

	Optional<Person> getPersonById(int id);

	Person updatePerson(int id, Person person);

	void deletePerson(int id);
}
