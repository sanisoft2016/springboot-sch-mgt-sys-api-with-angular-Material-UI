package com.springbootschmgtsysapi.service;

import java.util.List;
import java.util.Optional;

import com.springbootschmgtsysapi.entity.Student;

public interface IStudentService {

	List<Student> GetAllStudent();

	Optional<Student> GetStudentById(Integer id);
	int createStudent(Student Students);

	String updateStudent(Student Students);

	void deleteStudentById(Integer id);

	void deleteAllStudents();
	List<Student> findStudentsByName (String NameString);
	List<Student> findStudentsByClassId (int classId);
}