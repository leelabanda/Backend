//package com.spring.main.service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.ss.formula.functions.Today;
//import org.springframework.stereotype.Service;
//
//import com.spring.main.dto.AniversaryDto;
//import com.spring.main.dto.DashBoardResponse;
//import com.spring.main.dto.DashResponse;
//import com.spring.main.dto.DateOfBirthDto;
//import com.spring.main.entity.Person;
//import com.spring.main.iservice.IDashboardService;
//import com.spring.main.repo.DashBoardRepo;
//import com.spring.main.repo.PersonRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class DashboardService implements IDashboardService{
//	private final DashBoardRepo dashRepo;
//	private final PersonRepository personRepo;
////	private final DashResponse responseDash; 
//	@Override
//	public DashResponse getDashboardResponse() {
//		// TODO Auto-generated method stub
//		DashResponse response=new DashResponse();
//		response.setBirthdays(dashRepo.getUpcommingBirthdays());
//		response.setMarriageDates(dashRepo.getUpcommingMarriageAnniversaries());
//		return response;
//	}
//	@Override
//	public DashBoardResponse getUpcommingEvent() {
//		// TODO Auto-generated method stub
//		LocalDate today=LocalDate.now();
//		LocalDate endDate = today.plusDays(10);
////		List<AniversaryDto> aniversaries=new ArrayList<>();
////		List<DateOfBirthDto> birthdays=new ArrayList<>();
//	    List<DateOfBirthDto> motherBirthdays = new ArrayList<>();
//	    List<AniversaryDto> motherMarriageDates = new ArrayList<>();
//	    List<DateOfBirthDto> fatherBirthdays = new ArrayList<>();
//	    List<AniversaryDto> fatherMarriageDates = new ArrayList<>();
//		System.out.println("Today      : " + today);
//		System.out.println("End Date   : " + endDate);
//		List<Person> persons=personRepo.getMotherDashbboard();
//		for(Person person:persons) {
//			if(person.getDateOfBirth()!=null) {
//				LocalDate birthday=person.getDateOfBirth().withYear(today.getYear());
//				if(birthday.isBefore(today)) {
//					birthday=birthday.plusYears(1);
//				}
//				System.out.println(person.getName() + " -> " + birthday);
//				if ((birthday.isEqual(today) || birthday.isAfter(today))
//				        && (birthday.isEqual(endDate) || birthday.isBefore(endDate))) {
//
//					motherBirthdays.add(new DateOfBirthDto(
//				            person.getId(),
//				            person.getName(),
//				            birthday
//				    ));
//				}
//			}
//			if(person.getAniversaryDate()!=null) {
//				LocalDate anniversary=person.getAniversaryDate().withYear(today.getYear());
//				if(anniversary.isBefore(today)) {
//					anniversary=anniversary.plusYears(1);
//				}
//				if ((anniversary.isEqual(today) || anniversary.isAfter(today))
//				        && (anniversary.isEqual(endDate) || anniversary.isBefore(endDate))) {
//
//					motherMarriageDates.add(new AniversaryDto(
//				            person.getId(),
//				            person.getName(),
//				            anniversary
//				    ));
//				}
//			}
//		}
//		List<Person> fPersons=personRepo.getFatherDashboard();
//		for(Person person:fPersons) {
//			if(person.getDateOfBirth()!=null) {
//				LocalDate birthday=person.getDateOfBirth().withYear(today.getYear());
//				if(birthday.isBefore(today)) {
//					birthday=birthday.plusYears(1);
//				}
//				System.out.println(person.getName() + " -> " + birthday);
//				if ((birthday.isEqual(today) || birthday.isAfter(today))
//				        && (birthday.isEqual(endDate) || birthday.isBefore(endDate))) {
//
//					fatherBirthdays.add(new DateOfBirthDto(
//				            person.getId(),
//				            person.getName(),
//				            birthday
//				    ));
//				}
//			}
//			if(person.getAniversaryDate()!=null) {
//				LocalDate anniversary=person.getAniversaryDate().withYear(today.getYear());
//				if(anniversary.isBefore(today)) {
//					anniversary=anniversary.plusYears(1);
//				}
//				if ((anniversary.isEqual(today) || anniversary.isAfter(today))
//				        && (anniversary.isEqual(endDate) || anniversary.isBefore(endDate))) {
//
//					fatherMarriageDates.add(new AniversaryDto(
//				            person.getId(),
//				            person.getName(),
//				            anniversary
//				    ));
//				}
//			}
//		}
//		DashBoardResponse result=new DashBoardResponse();
//		result.setMotherBirthdays(motherBirthdays);
//		result.setMotherMarriageDates(motherMarriageDates);
//		result.setFatherBirthdays(fatherBirthdays);
//		result.setFatherMarriageDates(fatherMarriageDates);
//		return result;
//	}
//
//}
package com.spring.main.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.main.dto.AniversaryDto;
import com.spring.main.dto.DashBoardResponse;
import com.spring.main.dto.DashResponse;
import com.spring.main.dto.DateOfBirthDto;
import com.spring.main.dto.SummaryDto;
import com.spring.main.entity.com.spring.main.entity.Person;
import com.spring.main.iservice.IDashboardService;
import com.spring.main.repo.DashBoardRepo;
import com.spring.main.repo.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService implements IDashboardService {

    private final DashBoardRepo dashRepo;
    private final PersonRepository personRepo;
    private final ReuseCodeService reuseCode;

    @Override
    public DashResponse getDashboardResponse() {

        DashResponse response = new DashResponse();
        response.setBirthdays(dashRepo.getUpcommingBirthdays());
        response.setMarriageDates(dashRepo.getUpcommingMarriageAnniversaries());

        return response;
    }

    @Override
    public DashBoardResponse getUpcommingEvent() {

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(10);

        List<DateOfBirthDto> motherBirthdays = new ArrayList<>();
        List<AniversaryDto> motherMarriageDates = new ArrayList<>();

        List<DateOfBirthDto> fatherBirthdays = new ArrayList<>();
        List<AniversaryDto> fatherMarriageDates = new ArrayList<>();

        processPersons(
                personRepo.getMotherDashbboard(),
                today,
                endDate,
                motherBirthdays,
                motherMarriageDates);

        processPersons(
                personRepo.getFatherDashboard(),
                today,
                endDate,
                fatherBirthdays,
                fatherMarriageDates);

        DashBoardResponse response = new DashBoardResponse();

        response.setMotherBirthdays(motherBirthdays);
        response.setMotherMarriageDates(motherMarriageDates);
        response.setFatherBirthdays(fatherBirthdays);
        response.setFatherMarriageDates(fatherMarriageDates);

        return response;
    }

    /**
     * Common method to process birthdays and anniversaries.
     */
    private void processPersons(
            List<Person> persons,
            LocalDate today,
            LocalDate endDate,
            List<DateOfBirthDto> birthdays,
            List<AniversaryDto> anniversaries) {

        for (Person person : persons) {

            // Birthday
            if (person.getDateOfBirth() != null) {

                LocalDate birthday = person.getDateOfBirth().withYear(today.getYear());

                if (birthday.isBefore(today)) {
                    birthday = birthday.plusYears(1);
                }

                if (!birthday.isAfter(endDate)) {
                    birthdays.add(new DateOfBirthDto(
                            person.getId(),
                            person.getName(),
                            birthday,
                            person.getRelation()));
                }
            }

            // Anniversary
            if (person.getAniversaryDate() != null) {

                LocalDate anniversary = person.getAniversaryDate().withYear(today.getYear());

                if (anniversary.isBefore(today)) {
                    anniversary = anniversary.plusYears(1);
                }

                if (!anniversary.isAfter(endDate)) {
                    anniversaries.add(new AniversaryDto(
                            person.getId(),
                            person.getName(),
                            anniversary,person.getRelation()));
                }
            }
        }
    }

	@Override
	public DashBoardResponse getMotherDashboard() {
		DashResponse dash = reuseCode.buildDashboard(personRepo.getMotherDashbboard());

	    DashBoardResponse response = new DashBoardResponse();

	    response.setMotherBirthdays(dash.getBirthdays());
	    response.setMotherMarriageDates(dash.getMarriageDates());
	    response.setTodayMBirthdays(dash.getTodayDateOfBirth());
	    response.setTodayMMarriageDates(dash.getTodayAniversary());

	    return response;
	}

	@Override
	public DashBoardResponse getFatherDashboard() {
		DashResponse dash = reuseCode.buildDashboard(personRepo.getFatherDashboard());

	    DashBoardResponse response = new DashBoardResponse();

	    response.setFatherBirthdays(dash.getBirthdays());
	    response.setFatherMarriageDates(dash.getMarriageDates());
	    // Today's events
	    response.setTodayFBirthdays(dash.getTodayDateOfBirth());
	    response.setTodayFMarriageDates(dash.getTodayAniversary());
	    return response;
	}

	@Override
	public SummaryDto getDashboardSummary() {
		// TODO Auto-generated method stub
		SummaryDto dto=new SummaryDto();
		dto.setTotalUsers(personRepo.count());
		dto.setUpcommingBirthday(dashRepo.getUpcommingBirthdays().size());
		dto.setUpcommingAnniversaries(dashRepo.getUpcommingMarriageAnniversaries().size());
		return dto;
	}

	@Override
	public DashResponse getTodayEvents() {
		// TODO Auto-generated method stub
		List<Person> allPersons=personRepo.findAll();
		DashResponse fullDash=reuseCode.buildDashboard(allPersons);
		DashResponse todayOnly=new DashResponse();
		todayOnly.setTodayDateOfBirth(fullDash.getTodayDateOfBirth());
		todayOnly.setTodayAniversary(fullDash.getTodayAniversary());
		return todayOnly;
	}
}