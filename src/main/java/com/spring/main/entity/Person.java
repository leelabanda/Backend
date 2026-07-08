package com.spring.main.entity;

import java.time.LocalDate;
import java.time.MonthDay;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate dateOfBirth;
	private LocalDate aniversaryDate;
	private String relation;
	private String location;
	private String city;
	private String subRelation;
	
}
