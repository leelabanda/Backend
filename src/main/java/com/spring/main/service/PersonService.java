package com.spring.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.main.entity.com.spring.main.entity.Person;
import com.spring.main.iservice.IPersonService;
import com.spring.main.repo.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService{
	private final PersonRepository personRepo;

	@Override
	public Person create(Person person) {
		// TODO Auto-generated method stub
		return personRepo.save(person);
	}

	@Override
	public Person update(Long id, Person person) {
		// TODO Auto-generated method stub
		Person existingPerson=personRepo.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"+id));
		existingPerson.setName(person.getName());
		existingPerson.setDateOfBirth(person.getDateOfBirth());
		existingPerson.setAniversaryDate(person.getAniversaryDate());
		existingPerson.setRelation(person.getRelation());
		existingPerson.setLocation(person.getLocation());
		existingPerson.setSubRelation(person.getSubRelation());
		existingPerson.setCity(person.getCity());
		return personRepo.save(existingPerson);
	}

	@Override
	public List<Person> getall() {
		// TODO Auto-generated method stub
		return personRepo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Person existingPerson=personRepo.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
		personRepo.delete(existingPerson);
	}

	@Override
	public List<Person> getMotherDashboard() {
		// TODO Auto-generated method stub
		return personRepo.getMotherDashbboard();
	}

	@Override
	public List<Person> getFatherDashboard() {
		// TODO Auto-generated method stub
		return personRepo.getFatherDashboard();
	}

	@Override
	public Person getById(Long id) {
		// TODO Auto-generated method stub
		return personRepo.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
	}

	@Override
	public List<Person> search(String keyword) {
		// TODO Auto-generated method stub
		return personRepo.search(keyword);
	}
}
