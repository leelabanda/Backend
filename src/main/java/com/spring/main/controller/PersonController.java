package com.spring.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.entity.Person;
import com.spring.main.iservice.IPersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {
	private final IPersonService personService;
	
	@GetMapping("/all")
	public List<Person> getAll() {
		return personService.getall();
	}
	@GetMapping("/mother")
	public List<Person> getMotherAll(){
		return personService.getMotherDashboard();
	}
	@GetMapping("/father")
	public List<Person> getFatherAll(){
		return personService.getFatherDashboard();
	}
	@PutMapping("/{id}")
	public Person edit(@PathVariable Long id,@RequestBody Person person) {
		return personService.update(id, person);
	}
	@PostMapping("/add")
	public Person add(@RequestBody Person person) {
		return personService.create(person);
	}
	@GetMapping("/{id}")
	public Person getById(@PathVariable Long id) {
		return personService.getById(id);
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		personService.delete(id);
	}
	@GetMapping("/search")
	public List<Person> search(@RequestParam String keyword){
		return personService.search(keyword);
	}
}
