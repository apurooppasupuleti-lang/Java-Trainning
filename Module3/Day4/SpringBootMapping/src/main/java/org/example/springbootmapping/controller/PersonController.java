package org.example.springbootmapping.controller;

import org.example.springbootmapping.model.Person;
import org.example.springbootmapping.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	public Person addPerson(@RequestBody Person person) {
		return personService.addPerson(person);
	}

	@GetMapping
	public List<Person> getAll() {
		return personService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable int id) {
		return personService.getPersonById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public Person updatePerson(@PathVariable int id, @RequestBody Person person) {
		return personService.updatePerson(id, person);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable int id) {
		personService.deletePerson(id);
		return ResponseEntity.noContent().build();
	}
}
