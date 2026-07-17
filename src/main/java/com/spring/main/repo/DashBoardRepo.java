package com.spring.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.main.dto.AniversaryDto;
import com.spring.main.dto.DateOfBirthDto;
import com.spring.main.entity.com.spring.main.entity.Person;
import com.spring.main.entity.com.spring.main.entity.UserInfo;

public interface DashBoardRepo extends JpaRepository<Person,Long> {
    @Query("""
            SELECT new com.spring.main.dto.DateOfBirthDto(
                e.id,
                e.name,
                e.dateOfBirth,
                e.relation
            )
            FROM Person e
            WHERE MONTH(e.dateOfBirth) = MONTH(CURRENT_DATE)
            ORDER BY DAY(e.dateOfBirth)
        """)
	List<DateOfBirthDto> getUpcommingBirthdays();
    @Query("""
            SELECT new com.spring.main.dto.AniversaryDto(
                e.id,
                e.name,
                e.aniversaryDate,
                e.relation
            )
            FROM Person e
            WHERE e.aniversaryDate IS NOT NULL
            AND MONTH(e.aniversaryDate) = MONTH(CURRENT_DATE)
            ORDER BY DAY(e.aniversaryDate)
        """)
	List<AniversaryDto> getUpcommingMarriageAnniversaries();
    @Query("""
    	    SELECT p
    	    FROM Person p
    	    WHERE MONTH(p.dateOfBirth) = MONTH(CURRENT_DATE)
    	      AND DAY(p.dateOfBirth) = DAY(CURRENT_DATE)
    	""")
    	List<Person> getTodayBirthdays();
    @Query("""
    	    SELECT p
    	    FROM Person p
    	    WHERE p.aniversaryDate IS NOT NULL
    	      AND MONTH(p.aniversaryDate) = MONTH(CURRENT_DATE)
    	      AND DAY(p.aniversaryDate) = DAY(CURRENT_DATE)
    	""")
    	List<Person> getTodayAnniversaries();
}
