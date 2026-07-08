package com.spring.main.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashBoardResponse {

    private List<DateOfBirthDto> motherBirthdays;
    private List<AniversaryDto> motherMarriageDates;

    private List<DateOfBirthDto> fatherBirthdays;
    private List<AniversaryDto> fatherMarriageDates;
    
    private List<DateBirthDto> todayMBirthdays;
    private List<AniverDto> todayMMarriageDates;

    private List<DateBirthDto> todayFBirthdays;
    private List<AniverDto> todayFMarriageDates; 
}
