package org.example.springbootmapping.service;

import org.example.springbootmapping.model.Person;
import org.example.springbootmapping.respository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Person addPerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@Override
	public Optional<Person> getPersonById(int id) {
		return personRepository.findById(id);
	}

	@Override
	public Person updatePerson(int id, Person person) {
		return personRepository.findById(id)
				.map(existingPerson -> {
					existingPerson.setFname(person.getFname());
					existingPerson.setLname(person.getLname());
					existingPerson.setAge(person.getAge());
					existingPerson.setEmail(person.getEmail());
					return personRepository.save(existingPerson);
				})
				.orElseGet(() -> {
					person.setId(id);
					return personRepository.save(person);
				});
	}

	@Override
	public void deletePerson(int id) {
		personRepository.deleteById(id);
	}
}
