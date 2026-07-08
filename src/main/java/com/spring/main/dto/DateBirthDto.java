package com.spring.main.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateBirthDto {
	 private Long id;
	    private String name;
	    private String relation;
	    private String subRelation;
}
