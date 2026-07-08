package com.spring.main.dto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AniverDto {
	 private Long id;
	    private String name;
	    private String relation;
	    private String subRelation;
}
