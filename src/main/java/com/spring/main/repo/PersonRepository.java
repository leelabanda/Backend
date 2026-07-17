package com.spring.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.entity.com.spring.main.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	//@Query("""Select p from Person p where p.relation in ('M-Friend','Relation')""")
	@Query("SELECT p FROM Person p WHERE p.relation IN ('M-Friend', 'Relation')")
	List<Person> getMotherDashbboard();
	@Query("Select p from Person p where p.relation in('F-Friend','Relation')")
	List<Person> getFatherDashboard();
	@Query("""
			SELECT p FROM Person p
			WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
			   OR LOWER(p.relation) LIKE LOWER(CONCAT('%', :keyword, '%'))
			   OR LOWER(p.city) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
			List<Person> search(@Param("keyword") String keyword);
}
