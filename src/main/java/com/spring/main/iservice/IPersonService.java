package com.spring.main.iservice;

import java.util.List;

import com.spring.main.entity.com.spring.main.entity.Person;

public interface IPersonService {
	Person create(Person person);
	Person update(Long id,Person person);
	List<Person> getall();
	void delete(Long id);
	List<Person> getMotherDashboard();
	List<Person> getFatherDashboard();
	Person getById(Long id);
	List<Person>search(String keyword);
}
