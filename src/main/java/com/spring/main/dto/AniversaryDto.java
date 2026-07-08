package com.spring.main.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AniversaryDto {
	private long id;
	private String name;
	private LocalDate aniversaryDate;
	private String relation;
}
