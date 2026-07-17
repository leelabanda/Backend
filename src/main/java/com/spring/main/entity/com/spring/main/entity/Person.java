package com.spring.main.entity.com.spring.main.entity;

import java.time.LocalDate;
import java.time.MonthDay;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
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
