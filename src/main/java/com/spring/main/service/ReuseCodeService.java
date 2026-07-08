package com.spring.main.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.main.dto.AniverDto;
import com.spring.main.dto.AniversaryDto;
import com.spring.main.dto.DashResponse;
import com.spring.main.dto.DateBirthDto;
import com.spring.main.dto.DateOfBirthDto;
import com.spring.main.entity.Person;

@Service
public class ReuseCodeService {
	public DashResponse buildDashboard(List<Person> persons) {

	    LocalDate today = LocalDate.now();
	    LocalDate endDate = today.plusDays(10);

	    List<DateOfBirthDto> birthdays = new ArrayList<>();
	    List<AniversaryDto> anniversaries = new ArrayList<>();
	    List<DateBirthDto> todaysBirthdays = new ArrayList<>();
        List<AniverDto> todaysAnniversaries = new ArrayList<>();
	    for (Person person : persons) {

	        if (person.getDateOfBirth() != null) {

	            LocalDate birthday = person.getDateOfBirth().withYear(today.getYear());
	        //    System.out.println("Today is: " + today);
	        //    System.out.println("Calculated Birthday for comparison: " + birthday);
	            if (birthday.isBefore(today)) {
	                birthday = birthday.plusYears(1);
	            }

	            if (!birthday.isAfter(endDate)) {
	               DateOfBirthDto dto=new DateOfBirthDto(person.getId(),person.getName(),birthday,person.getRelation());
	               birthdays.add(dto);
	               if(birthday.isEqual(today)) {
	            	   DateBirthDto dto1=new DateBirthDto(person.getId(),person.getName(),person.getRelation(),person.getSubRelation());
		            	todaysBirthdays.add(dto1);
		            }         
	            }
	        }

	        if (person.getAniversaryDate() != null) {

	            LocalDate anniversary = person.getAniversaryDate().withYear(today.getYear());

	            if (anniversary.isBefore(today)) {
	                anniversary = anniversary.plusYears(1);
	            }

	            if (!anniversary.isAfter(endDate)) {
	            	
	            	AniversaryDto aniversary=new AniversaryDto(person.getId(),person.getName(),anniversary,person.getRelation());
	            	anniversaries.add(aniversary);
	            	if(anniversary.isEqual(today)) {
	            		AniverDto dto2=new AniverDto(person.getId(),person.getName(),person.getRelation(),person.getSubRelation());
	            		todaysAnniversaries.add(dto2);
	            	}
	        }
	    }
	}
	    DashResponse response = new DashResponse();
	    response.setTodayDateOfBirth(todaysBirthdays);
        response.setTodayAniversary(todaysAnniversaries);
	    response.setBirthdays(birthdays);
	    response.setMarriageDates(anniversaries);

	    return response;
}
}
