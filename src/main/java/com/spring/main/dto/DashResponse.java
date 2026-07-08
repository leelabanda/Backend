package com.spring.main.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashResponse {
    private List<DateOfBirthDto> birthdays;
    private List<AniversaryDto> marriageDates;
    private List<AniverDto> todayAniversary;
    private List<DateBirthDto> todayDateOfBirth;
}
