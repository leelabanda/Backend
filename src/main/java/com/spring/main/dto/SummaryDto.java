package com.spring.main.dto;

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
public class SummaryDto {
	private Long totalUsers;
	private Integer upcommingBirthday;
	private Integer upcommingAnniversaries;
}
