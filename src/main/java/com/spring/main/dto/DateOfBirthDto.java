package com.spring.main.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateOfBirthDto {
	private Long id;
	private String name;
	private LocalDate dateOfBirth;
	private String relation;
}
