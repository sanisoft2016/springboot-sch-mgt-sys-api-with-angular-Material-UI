package com.springbootschmgtsysapi.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springbootschmgtsysapi.entity.Student;

public interface StudentRepository   extends JpaRepository<Student, Integer>{
	Optional<Student> findByAdmissionNo(String className);
	Optional<Student> findByAdmissionNoAndIdNot(String className, int id);
	
	@JsonIgnoreProperties("student")
	List<Student> findByFirstNameOrLastName(String firstName, String lastName);
	List<Student> findByCurrentClassId(int classId);
}
